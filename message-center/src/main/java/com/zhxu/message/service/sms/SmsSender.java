package com.zhxu.message.service.sms;

import com.alibaba.fastjson.JSONObject;
import com.zhxu.message.model.sms.SmsParam;
import com.zhxu.message.utils.MD5Util;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SmsSender{

    @Autowired
    private RestTemplate restTemplate;

    public void send(SmsParam param) {

        // 5：短信发送授权获取mas_user_id用户登录id
        String authorizeUrl = param.getAuthorizeUrl() + "?ec_name=" + param.getOrganizationName() + "&user_name=" + param.getAuthorizeName() + "&user_passwd=" + param.getAuthorizePassword();
        JSONObject authorize = this.restTemplate.getForObject(authorizeUrl, JSONObject.class);
        // 6：获取用户登录ID
        String mas_user_id = authorize.getString("mas_user_id");
        // 7：获取access_token
        String access_token = authorize.getString("access_token");

//        Map<String, String> params = new HashMap<>(8);
//        params.put("mas_user_id", mas_user_id);
//        params.put("content", param.getContent());
//        params.put("sign", param.getSign());
//        params.put("serial", "");

        Iterator<String> mobiles = param.getMobiles().iterator();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(() -> {
            if (mobiles.hasNext()) {
                String mobile = mobiles.next();
//                params.put("mobiles", mobile);
//                params.put("mac", MD5Util.md5toUpperCase32(mas_user_id + mobile +
//                        param.getContent() + param.getSign() + access_token));
                String mac = MD5Util.md5toUpperCase32(mas_user_id + mobile + param.getContent() + param.getSign() + "" + access_token);
                String sendUrl = param.getUrl() + "?mas_user_id=" + mas_user_id + "&mobiles=" + mobile + "&content=" + param.getContent() + "&sign=" + param.getSign() + "&serial=&mac=" + mac;
                JSONObject jsonObject = restTemplate.postForObject(sendUrl, "", JSONObject.class);

            } else {
                scheduledExecutorService.shutdown();
            }
        }, 100, TimeUnit.MILLISECONDS);
    }
}
