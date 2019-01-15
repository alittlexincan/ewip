package com.zhxu.message.service.sms;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhxu.message.MsgHandler;
import com.zhxu.message.entity.ChannelConfig;
import com.zhxu.message.modal.ChannelType;
import com.zhxu.message.modal.Message;
import com.zhxu.message.model.sms.SmsConfig;
import com.zhxu.message.model.sms.SmsParam;
import com.zhxu.message.repository.ChannelConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmsHandler implements MsgHandler {

    @Autowired
    private ChannelConfigRepository channelConfigRepository;

    @Autowired
    private SmsSender smsSender;

    @Override
    public void handle(Message msg) {

        ChannelConfig config = channelConfigRepository.findByAreaIdAndChannelType(msg.getArea().getId(), ChannelType.SMS.getType());

        List<String> mobiles = new ArrayList();

        msg.getGroups().forEach(group -> {
            group.getUsers().forEach(user -> {
                mobiles.add(user.getMobile());
            });
        });

        ObjectMapper mapper = new ObjectMapper();
        SmsConfig smsConfig = null;
        try {
            smsConfig = mapper.readValue(config.getContent(), SmsConfig.class);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (smsConfig == null) {
            return;
        }

        SmsParam param = SmsParam.builder()
                .url(smsConfig.getAuthorizeUrl())
                .sign(smsConfig.getSign())
                .organizationName(smsConfig.getOrganizationName())
                .authorizeName(smsConfig.getAuthorizeUserName())
                .authorizePassword(smsConfig.getAuthorizeUserPassword())
                .content(msg.getContent())
                .mobiles(mobiles)
                .build();

        smsSender.send(param);
    }
}
