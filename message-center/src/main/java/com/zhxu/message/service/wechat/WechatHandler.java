package com.zhxu.message.service.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhxu.message.entity.ChannelConfig;
import com.zhxu.message.model.wechat.WechatConfig;
import com.zhxu.message.model.wechat.WechatMessage;
import com.zhxu.message.model.wechat.WechatParam;
import com.zhxu.message.repository.ChannelConfigRepository;
import com.zhxu.model.message.ChannelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WechatHandler {

    @Autowired
    private ChannelConfigRepository channelConfigRepository;

    @Autowired
    private WechatSender wechatSender;

    @Autowired
    private RestTemplate restTemplate;


    public void handle(WechatMessage msg) {
        JSONObject result = new JSONObject();

        ChannelConfig config = channelConfigRepository.findByAreaIdAndChannelType(msg.getAreaId(), ChannelType.WECHAT.getName());
        WechatConfig wechatConfig = JSON.parseObject(config.getContent(), WechatConfig.class);

        // 获取accessToken
        String tokenUrl = wechatConfig.getTokenUrl() + "?grant_type=client_credential&appid=" + wechatConfig.getAppId() + "&secret=" + wechatConfig.getAppSecret();
        JSONObject token = this.restTemplate.getForObject(tokenUrl,JSONObject.class);
        String accessToken = token.getString("access_token");

        if(accessToken == null){
            result.put("messageId", "id");
            result.put("channelCode", "WECHAT");
            result.put("total", 1);
            result.put("success", 0);
            result.put("fail", 1);
            result.put("work","微信推送获取TOKEN失败");
        }

        // 获取所有关注用户信息，提取用户openId: 如果okUser有值，则说明是手动发送
        List<String> userList = wechatConfig.getOkUser().length() > 0 ? Arrays.asList(wechatConfig.getOkUser().split(",")) : getUserList(wechatConfig.getUserListUrl(), accessToken, "");

        WechatParam param = WechatParam.builder()
                .serviceTemplate(wechatConfig.getServiceTemplate())
                .templateUrl(wechatConfig.getTemplateUrl())
                .number(wechatConfig.getNumber())
                .title(msg.getTitle())
                .template(msg.getTemplate())
                .content(msg.getContent())
                .accessToken(accessToken)
                .userList(userList)
                .build();

        wechatSender.send(param);

    }

    /**
     * 递归获取所有用户openId
     * @param accessToken
     * @param userOpenId  下一个openId
     */
    private List<String> getUserList(String userListUrl, String accessToken, String userOpenId){
        List<String> userList = new ArrayList<>();
        Integer count = -1, total = 0;
        String next_openId = userOpenId;
        while (count==-1 || count > 0){
            if(total.equals(count)){ break;}
            String userUrl = userListUrl + "?access_token=" + accessToken + "&next_openid=" + next_openId;
            JSONObject users = this.restTemplate.getForObject(userUrl, JSONObject.class);
            next_openId = users.getString("next_openid");
            count = users.getInteger("count");
            total = users.getInteger("total");
            if(next_openId == null){
                count = 0;
            }else {
                if(count == 0){return userList;}
                JSONArray userArray = users.getJSONObject("data").getJSONArray("openid");
                List<String> list = JSONObject.parseArray(userArray.toJSONString(), String.class);
                userList.addAll(list);
            }
        }
        return userList;
    }
}
