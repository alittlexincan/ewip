package com.zhxu.message.service.email;

import com.alibaba.fastjson.JSON;
import com.zhxu.message.MsgHandler;
import com.zhxu.message.entity.ChannelConfig;
import com.zhxu.message.modal.ChannelType;
import com.zhxu.message.modal.Message;
import com.zhxu.message.model.email.EmailConfig;
import com.zhxu.message.model.email.EmailParam;
import com.zhxu.message.model.sms.SmsConfig;
import com.zhxu.message.model.sms.SmsParam;
import com.zhxu.message.repository.ChannelConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class EmailHandler  implements MsgHandler {

    @Autowired
    private ChannelConfigRepository channelConfigRepository;

    @Autowired
    private EmailSender emailSender;


    @Override
    public void handle(Message msg) {

        ChannelConfig config = channelConfigRepository.findByAreaIdAndChannelType(msg.getArea().getId(), ChannelType.EMAIL.getType());
        EmailConfig emailConfig = JSON.parseObject(config.getContent(), EmailConfig.class);

        List<String> emails = new ArrayList<>();

        msg.getGroups().forEach(group -> {
            group.getUsers().forEach(user -> {
                emails.add(user.getEmail());
            });
        });

        EmailParam param = EmailParam.builder()
                .host(emailConfig.getHost())
                .userName(emailConfig.getUserName())
                .password(emailConfig.getPassword())
                .port(emailConfig.getPort())
                .title(msg.getTitle())
                .content(msg.getContent())
                .emails(emails)
                .build();

        emailSender.send(param);

    }
}
