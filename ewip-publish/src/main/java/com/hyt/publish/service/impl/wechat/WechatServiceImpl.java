package com.hyt.publish.service.impl.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.publish.entity.WechatTemplate;
import com.hyt.publish.entity.WechatTemplateParam;
import com.hyt.publish.service.wechat.IWechatService;
import com.xincan.utils.disaster.DisasterUtil;
import com.xincan.utils.disaster.MsgTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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

    // 每批次发送条数(微信平台要求上限不超过1000条每批次)
    @Value("${wechat.number}")
    private Integer number;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Async
    public JSONObject wechat(JSONObject json) {

        JSONObject result = new JSONObject();
        // 存放用户openId列表
        List<String> userList = null;

        log.info("===============微信推送=====================");

        // 1：通过tokenUrl获取access_token
        log.info("获取微信tokenUrl: 【{}】",tokenUrl);
        JSONObject token = this.restTemplate.getForObject(tokenUrl,JSONObject.class);

        String accessToken = token.getString("access_token");
        if(accessToken == null){
            log.info("微信推送获取TOKEN失败！{}", token);
            json.put("status",500);
            json.put("message","微信推送失败");
            return result;
        }
        log.info("成功获取微信token: 【{}】" ,accessToken);

        // 2：获取所有关注用户信息，提取用户openId
        // 如果okUser有值，则说明是手动发送
        if(okUser.length() > 0){
            userList = Arrays.asList(okUser.split(","));
        }else{
            userList = getUserList(accessToken, "");
        }

        log.info("微信用户openId列表：{}",JSON.toJSON(userList));

        // 3：获取灾种信息
        JSONObject disaster = DisasterUtil.getDisasterInfo(json.getInteger("disasterColor"));

        // 获取灾种名称
        String disasterName = json.getString("disasterName");

        // 获取灾种颜色(rgb)
        String rgb = disaster.getString("rgb");

        // 获取灾种颜色(名称)
        String color = disaster.getString("color");

        // 获取用户总个数
        int count = userList.size();
        for (int i = 0; i < count; i += this.number) {
            if (i + this.number > count) {        //作用为number最后没有200条数据则剩余几条newList中就装几条
                this.number = count - i;
            }
            StringBuilder sb = new StringBuilder();
            userList.subList(i, i + this.number).forEach(u -> {
                sb.append("," + u);
                WechatTemplate tem = new WechatTemplate();
                tem.setTemplateId(warnTemplate);
                tem.setTopColor(rgb);
                tem.setToUser(u);
                tem.setUrl("");
                List<WechatTemplateParam> paras = new ArrayList<>();
                paras.add(new WechatTemplateParam("first",getTitle(json, disaster), rgb));
                paras.add(new WechatTemplateParam("keyword1",json.getString("organizationName"), rgb));
                paras.add(new WechatTemplateParam("keyword2",disasterName, rgb));
                paras.add(new WechatTemplateParam("keyword3",color, rgb));
                paras.add(new WechatTemplateParam("keyword4",json.getString("sendTime"), rgb));
                paras.add(new WechatTemplateParam("remark",json.getJSONObject("content").getString("content"), rgb));
                tem.setTemplateParamList(paras);
                log.info("模板信息：" + tem.toJSON());
                ResponseEntity<JSONObject> rest = this.restTemplate.postForEntity(templateUrl.replace("{accessToken}", accessToken), tem.toJSON(), JSONObject.class);
                log.info("微信推送回执结果：" + rest.getBody().toJSONString());
            });
            log.info("微信每批次发送用户：" + sb.toString().substring(1));
        }

        log.info("微信推送成功");
        json.put("status", 200);
        json.put("message","微信推送成功");
        return json;
    }


    /**
     * 递归获取所有用户openId
     * @param accessToken
     * @param userOpenId  下一个openId
     */
    private List<String> getUserList(String accessToken, String userOpenId){
        List<String> userList = new ArrayList<>();
        Integer count = -1, total = 0;
        String next_openId = userOpenId;
        while (count==-1 || count > 0){
            if(total.equals(count)) break;
            String userUrl = this.userListUrl.replace("{accessToken}", accessToken).replace("{userOpenId}",next_openId);
            JSONObject users = this.restTemplate.getForObject(userUrl, JSONObject.class);
            next_openId = users.getString("next_openid");
            count = users.getInteger("count");
            total = users.getInteger("total");
            if(next_openId == null){
                count = 0;
                log.info("微信获取用户列表失败  {}", users);
            }else {
                if(count == 0)return userList;
                JSONArray userArray = users.getJSONObject("data").getJSONArray("openid");
                List<String> list = JSONObject.parseArray(userArray.toJSONString(), String.class);
                userList.addAll(list);
            }
        }
        return userList;
    }


    /**
     * 获取标题
     *
     * @param json
     * @return
     */
    private String getTitle(JSONObject json, JSONObject disaster){
        String title = json.getString("organizationName");
        String msgType = json.getString("msgType");
        title += MsgTypeUtil.parseMsgType(msgType);
        title += json.getString("disasterName");
        title += disaster.getString("color");
        title += "预警信号";
        title += disaster.getString("level");
        return title;
    }
}
