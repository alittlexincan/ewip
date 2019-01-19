package com.zhxu.message.service.record;

import com.alibaba.fastjson.JSONObject;
import com.zhxu.message.model.record.Ftp;
import com.zhxu.message.model.record.RecordParam;
import com.zhxu.message.utils.Dom4jXmlUtil;
import com.zhxu.message.utils.FtpOfFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("recordSender")
public class RecordSender {
    @Autowired
    private RestTemplate restTemplate;

    public void send(RecordParam param) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("platformID",1);
        jsonObject.put("identifier",2);
        jsonObject.put("sender",3);
        jsonObject.put("senderCode",4);

        jsonObject.put("sendTime",4);
        jsonObject.put("expires",5);
        jsonObject.put("msgType",6);
        jsonObject.put("references",7);

        jsonObject.put("almType",8);
        jsonObject.put("channelPlatformID",9);
        jsonObject.put("methodType",1);
        jsonObject.put("methodName",1);

        jsonObject.put("message",1);
        jsonObject.put("devID",1);
        jsonObject.put("eventType",1);
        jsonObject.put("severity",1);

        jsonObject.put("headline",1);
        jsonObject.put("digest",1);
        jsonObject.put("size",1);
        jsonObject.put("resourceDesc",1);

        jsonObject.put("areaDesc",1);
        jsonObject.put("geocode",1);
        jsonObject.put("path","E://aaaa//warn.xml");

        Ftp ftp = new Ftp();
        ftp.setHost(param.getHost());
        ftp.setPort(param.getPort());
        ftp.setUser(param.getUser());
        ftp.setPassword(param.getPassword());

        try {
            Dom4jXmlUtil.createXml(jsonObject);
            boolean flag = FtpOfFileUtil.uploadFile("E:/aaaa/warn.xml", "warn.xml", ftp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
