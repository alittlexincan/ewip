package com.hyt.publish.service.impl.sms;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.publish.service.sms.ISmsService;
import com.xincan.utils.md5.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @Author: JiangXincan
 * @Description: 发布渠道：短信接口实现类
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Slf4j
@Service("smsService")
public class SmsServiceImpl implements ISmsService {


    @Value("${sms.ec.name}")
    private String ecName;

    @Value("${sms.user.name}")
    private String userName;

    @Value("${sms.user.password}")
    private String userPassword;

    /**
     * 短信发送签名
     */
    @Value("${sms.sign}")
    private String sign;

    /**
     * 短信授权用户名称
     */
    @Value("${sms.http.authorize.user.name}")
    private String authorizeUserName;

    /**
     * 短信授权用户密码
     */
    @Value("${sms.http.authorize.user.password}")
    private String authorizeUserPassword;

    /**
     * 短信授权接口调用URL
     */
    @Value("${sms.authorize.url}")
    private String authorizeUrl;

    /**
     * 短信发送接口调用URL
     */
    @Value("${sms.send.url}")
    private String sendUrl;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 短信发送
     * 1：解析数据
     * 2：短信发送授权
     * 3：短信发送
     * @param json
     */
    @Override
    @Async
    public JSONObject sms(JSONObject json) {
        // 返回结果信息
        JSONObject result = new JSONObject();
        // 1：解析数据
        JSONObject sendMessage = sendMessage(json);
        // 获取发送用户和内容
        String userList = sendMessage.getString("userList"),
        content = sendMessage.getString("content");
        // 2：短信发送授权获取mas_user_id用户登录id
        String authorizeUrl = this.authorizeUrl + "?ec_name=" + this.ecName + "&user_name=" + this.authorizeUserName + "&user_passwd=" + this.authorizeUserPassword;
        log.info("短信授权URL：【" + authorizeUrl + "】");
        JSONObject authorize = this.restTemplate.getForObject(authorizeUrl,JSONObject.class);
        log.info("短信授权返回信息：【" + authorizeUrl +"】");
        // 获取用户登录ID
        String mas_user_id = authorize.getString("mas_user_id");
        // 获取access_token
        String access_token = authorize.getString("access_token");
        if(mas_user_id == null){
            result.put("status",500);
            result.put("message","短信发送授权失败");
            return result;
        }
        // 3：短信加密属性
        String mac = MD5Util.md5toUpperCase32(mas_user_id + userList + content + this.sign + "" + access_token);
        // 4：短信发送路径
        String sendUrl = this.sendUrl + "?mas_user_id=" +mas_user_id + "&mobiles=" + userList + "&content=" + content + "&sign=" + this.sign + "&serial=&mac=" + mac;
        log.info("短信发送URL：【" + sendUrl + "】");
        // 5：短信发送
        JSONObject send = this.restTemplate.postForObject(sendUrl,"", JSONObject.class);
        log.info("短信发送返回信息：【" + send +"】");
        String retCode = send.getString("RET-CODE");
        if(retCode == null){
            log.info("短信发送失败");
            json.put("status",500);
            json.put("message","短信发送失败");
            return json;
        }
        log.info("短信发送成功");
        json.put("status", 200);
        json.put("message","短信发送成功");
        return json;
    }

    /**
     * 获取短信发送信息和受众
     * @param param
     * @return
     */
    private JSONObject sendMessage(JSONObject param){
        JSONObject result = new JSONObject();
        // 获取发送内容
        String content = param.getJSONObject("content").getString("content");
        // 获取发送受众
        JSONObject user = param.getJSONObject("user");
        String userList = "";
        for (String u : user.keySet()) {
            JSONArray userArray = user.getJSONArray(u);
            for(int i = 0; i < userArray.size(); i++){
                JSONObject usr = userArray.getJSONObject(i);
                userList += "," + usr.getString("userCode");
            }
        }
        result.put("content", content);
        result.put("userList", userList.substring(1));
        return result;
    }

}
