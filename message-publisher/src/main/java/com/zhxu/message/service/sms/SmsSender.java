package com.zhxu.message.service.sms;

import com.alibaba.fastjson.JSONObject;
import com.zhxu.message.modal.SmsParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service("smsSender")
public class SmsSender{

    @Autowired
    private RestTemplate restTemplate;

    public void send(SmsParam param) {
        Map<String, String> params = new HashMap<>(8);
        params.put("mas_user_id", param.getMasUserId());
        params.put("content", param.getContent());
        params.put("sign", param.getSign());
        params.put("serial", "");
        Iterator<String> mobiles = param.getMobiles().iterator();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(() -> {
            if (mobiles.hasNext()) {
                String mobile = mobiles.next();
                params.put("mobiles", mobile);
                params.put("mac", DigestUtils.md5Hex(param.getMasUserId() + mobile +
                        param.getContent() + param.getSign() + param.getAccessToken()));
                restTemplate.postForObject(param.getUrl(), "", JSONObject.class, params);
            } else {
                scheduledExecutorService.shutdown();
            }
        }, 100, TimeUnit.MILLISECONDS);
    }
}
