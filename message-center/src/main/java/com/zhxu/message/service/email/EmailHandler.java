package com.zhxu.message.service.email;

import com.alibaba.fastjson.JSON;
import com.zhxu.message.entity.ChannelConfig;
import com.zhxu.message.model.email.EmailMessage;
import com.zhxu.model.message.ChannelType;
import com.zhxu.message.model.email.EmailConfig;
import com.zhxu.message.model.email.EmailParam;
import com.zhxu.message.repository.ChannelConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailHandler {

    @Autowired
    private ChannelConfigRepository channelConfigRepository;

    @Autowired
    private EmailSender emailSender;

    public void handle(EmailMessage msg) {

        ChannelConfig config = channelConfigRepository.findByAreaIdAndChannelType(msg.getAreaId(), ChannelType.EMAIL.getName());
        EmailConfig emailConfig = JSON.parseObject(config.getContent(), EmailConfig.class);

        if (emailConfig == null) {
            return;
        }

        EmailParam param = EmailParam.builder()
                .host(emailConfig.getHost())
                .userName(emailConfig.getUserName())
                .password(emailConfig.getPassword())
                .port(emailConfig.getPort())
                .title(msg.getTitle())
                .content(msg.getContent())
                .emails(msg.getEmails())
                .build();

        emailSender.send(param);

    }
}
