package com.zhxu.message.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhxu.message.model.email.EmailMessage;
import com.zhxu.message.model.record.RecordMessage;
import com.zhxu.message.model.sms.SmsMessage;
import com.zhxu.message.model.wechat.WechatMessage;
import com.zhxu.message.model.weibo.sina.SinaWeiboMessage;
import com.zhxu.message.service.email.EmailHandler;
import com.zhxu.message.service.record.RecordHandler;
import com.zhxu.message.service.weibo.sina.SinaWeiboHandler;
import com.zhxu.model.message.PublishInfo;
import com.zhxu.message.service.sms.SmsHandler;
import com.zhxu.message.service.wechat.WechatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    SmsHandler smsHandler;

    @Autowired
    EmailHandler emailHandler;

    @Autowired
    WechatHandler wechatHandler;

    @Autowired
    SinaWeiboHandler sinaWeiboHandler;

    @Autowired
    RecordHandler recordHandler;

    @PostMapping("/")
    public void publish(@RequestBody JSONObject object){

        object.put("files", JSON.parseArray(object.getString("files"), PublishInfo.Attachment.class));
        PublishInfo info = JSON.parseObject(JSON.toJSONString(object), PublishInfo.class);

        if (info == null) {
            return;
        }

        doPublish(info);
    }

    private void doPublish(PublishInfo info) {
        info.getAreas().forEach(area -> {
            info.getChannels().forEach(channel -> {
                String areaId = area.getId();
                String content = info.getContent() != null ? info.getContent() :
                        info.getContents().get(area.getId());
                switch (channel.getType()) {
                    case SMS: {
                        Set<String> mobiles = new HashSet<>();
                        info.getGroups().get(channel.getId()).forEach(group -> {
                            info.getUsers().get(group.getId()).forEach(user -> {
                                mobiles.add(user.getUserCode());
                            });
                        });
                        SmsMessage smsMessage = new SmsMessage();
                        smsMessage.setAreaId(areaId);
                        smsMessage.setContent(content);
                        smsMessage.setMobiles(mobiles);
                        smsHandler.handle(smsMessage);
                    }
                    case EMAIL: {
                        Set<String> emails = new HashSet<>();
                        info.getGroups().get(channel.getId()).forEach(group -> {
                            info.getUsers().get(group.getId()).forEach(user -> {
                                emails.add(user.getUserCode());
                            });
                        });
                        EmailMessage emailMessage = new EmailMessage();
                        emailMessage.setAreaId(areaId);
                        emailMessage.setContent(content);
                        emailMessage.setEmails(emails);
                        emailMessage.setTitle(info.getTitle());
                        emailHandler.handle(emailMessage);
                    }
                    case WECHAT: {
                        WechatMessage wechatMessage = new WechatMessage();
                        wechatMessage.setAreaId(areaId);
                        wechatMessage.setContent(content);
                        wechatMessage.setTitle(info.getTitle());
                        wechatMessage.setTemplate(info.getTemplate());
                        wechatHandler.handle(wechatMessage);
                    }
                    case WEIBO: {
                        SinaWeiboMessage sinaWeiboMessage = new SinaWeiboMessage();
                        sinaWeiboMessage.setAreaId(areaId);
                        sinaWeiboMessage.setContent(content);
                        sinaWeiboHandler.handle(sinaWeiboMessage);
                    }
                    case RECORD: {
                        RecordMessage recordMessage = new RecordMessage();
                        recordMessage.setAreaId(areaId);
                        recordMessage.setContent(content);
                        recordMessage.setTitle(info.getTitle());
                        recordHandler.handle(recordMessage);
                    }
                }
            });
        });
    }
}
