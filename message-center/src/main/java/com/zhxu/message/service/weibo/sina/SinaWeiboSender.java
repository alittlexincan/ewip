package com.zhxu.message.service.weibo.sina;

import com.alibaba.fastjson.JSONObject;
import com.zhxu.message.model.weibo.sina.SinaWeiboParam;
import com.zhxu.message.utils.XinLangWeiBoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("sinaWeiBoSender")
public class SinaWeiboSender {

    @Autowired
    private RestTemplate restTemplate;

    public void sendSinaWeiBo(SinaWeiboParam param) {

//        Map<String, String> params = new HashMap<>(8);
//
//        params.put("access_token", param.getAccess_token());
//        params.put("status", param.getContent() + param.getSafeUrl());

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//        headers.add("accept", "*/*");
//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);


//        JSONObject jsonObject = restTemplate.postForObject(param.getSinaSendUrl(), "", JSONObject.class, params);
//        ResponseEntity<JSONObject> response = restTemplate.postForEntity(param.getSinaSendUrl(), entity, JSONObject.class);
//        System.out.println(response);

        JSONObject jsonObject = new JSONObject();


        jsonObject.put("sinaSendUrl", param.getSinaSendUrl());
        jsonObject.put("safeUrl", param.getSafeUrl());
        jsonObject.put("access_token", param.getAccess_token());
        jsonObject.put("content", param.getContent());


        JSONObject result = null;
        try {
            result = XinLangWeiBoUtil.sendPost(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

}
