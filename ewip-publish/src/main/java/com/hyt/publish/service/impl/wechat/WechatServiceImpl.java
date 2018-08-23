package com.hyt.publish.service.impl.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.publish.entity.WechatTemplate;
import com.hyt.publish.entity.WechatTemplateParam;
import com.hyt.publish.service.wechat.IWechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: JiangXincan
 * @Description: 发布渠道：微信接口实现层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Slf4j
@Service("wechatService")
public class WechatServiceImpl implements IWechatService {

    @Value("${wechat.url}")
    private String url;

    @Value("${wechat.port}")
    private String port;

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    // 气象灾害预警提醒
    @Value("${wechat.weather.template}")
    private String weatherTemplate;

    // 服务提醒通知
    @Value("${wechat.service.template}")
    private String serviceTemplate;

    // 突发事件预警提醒
    @Value("${wechat.warn.template}")
    private String warnTemplate;

    // 获取access_token url地址
    @Value("${wechat.token.url}")
    private String tokenUrl;

    // 模板请求路径
    @Value("${wechat.template.url}")
    private String templateUrl;

    // 用户OPENID列表信息地址

    @Value("${wechat.user.list.url}")
    private String userListUrl;

    // 单个用户信息地址
    @Value("${wechat.user.info.url}")
    private String userInfoUrl;

    // 发送指定用户（输入openId用英文逗号隔开）
    @Value("${wechat.ok.user}")
    private String okUser;

    // 存放用户openId列表
    private List<String> userList = new ArrayList<>();

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Async
    public void wechat(JSONObject json) {

        log.info("===============微信推送=====================");

        // 1：通过tokenUrl获取access_token
        log.info("获取微信tokenUrl: 【{}】",tokenUrl);
        JSONObject token = this.restTemplate.getForObject(tokenUrl,JSONObject.class);

        String accessToken = token.getString("access_token");
        if(accessToken == null){
            log.info("微信推送获取TOKEN失败！{}", token);
            return;
        }
        log.info("成功获取微信token: 【{}】" ,accessToken);


        // 2：获取所有关注用户信息，提取用户openId

        if(okUser.length() > 0){
            userList = Arrays.asList(okUser.split(","));
        }else{
            getUserList(accessToken, "");
        }


        log.info("微信用户openId列表：{}",JSON.toJSON(userList));


        // 3：获取灾种颜色
        String disasterColor = getColor(json.getInteger("disasterColor"));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userList.forEach(
            u -> {
                WechatTemplate tem = new WechatTemplate();
                tem.setTemplateId(warnTemplate);
                tem.setTopColor(disasterColor);
                tem.setToUser("o8OZjuNAwTe2wmL5GMiswmHxEuKA");
                tem.setUrl("");
                List<WechatTemplateParam> paras = new ArrayList<>();
                paras.add(new WechatTemplateParam("first","测试：华池县气象局发布高温蓝色预警信号[V级/一般]",disasterColor));
                paras.add(new WechatTemplateParam("keyword1","华池县气象局",disasterColor));
                paras.add(new WechatTemplateParam("keyword2","高温",disasterColor));
                paras.add(new WechatTemplateParam("keyword3","蓝色",disasterColor));
                paras.add(new WechatTemplateParam("keyword4",(sdf.format(new Date())),"#0000FF"));
                paras.add(new WechatTemplateParam("remark","华池县气象台于2018年8月22日16时05分发布高温蓝色预警信号",disasterColor));
                tem.setTemplateParamList(paras);
                log.info(JSON.toJSON(tem).toString());
                ResponseEntity<JSONObject> result = this.restTemplate.postForEntity(templateUrl.replace("{accessToken}", accessToken), tem.toJSON(), JSONObject.class);
                log.info(result.getBody().toJSONString());
            }
        );
    }


    /**
     * 递归获取所有用户openId
     * @param accessToken
     * @param userOpenId  下一个openId
     */
    private void getUserList(String accessToken, String userOpenId){
        userListUrl = userListUrl.replace("{accessToken}", accessToken).replace("{userOpenId}",userOpenId);
        JSONObject users = this.restTemplate.getForObject(userListUrl, JSONObject.class);

        String nextOpenId = users.getString("next_openid");
        if(nextOpenId == null){
            log.info("微信获取用户列表失败  {}", users);
            return;
        }

        if(nextOpenId == ""){
            log.info("微信获取用户列表信息提取完毕");
            return;
        }else{
            if(users.getJSONObject("data").getJSONArray("openid").size() == 0){
                log.info("暂无微信关注用户");
                return;
            }else{
                JSONArray userArray = users.getJSONObject("data").getJSONArray("openid");
                for (int i = 0; i<userArray.size();i++){
                    String openId = userArray.getString(i);
                    userList.add(openId);
                }
                getUserList(accessToken, nextOpenId);
            }
        }
    }

    private String getColor(Integer disasterColor){
        if(disasterColor == 0) return "#FF0000";
        if(disasterColor == 1) return "#FFA500";
        if(disasterColor == 2) return "#FFFF00";
        if(disasterColor == 3) return "#0000FF";
        return null;

    }
}
