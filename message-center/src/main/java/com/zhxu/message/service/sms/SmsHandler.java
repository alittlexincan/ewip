package com.zhxu.message.service.sms;

import com.alibaba.fastjson.JSON;
import com.zhxu.message.entity.ChannelConfig;
import com.zhxu.message.model.sms.SmsMessage;
import com.zhxu.model.message.ChannelType;
import com.zhxu.message.model.sms.SmsConfig;
import com.zhxu.message.model.sms.SmsParam;
import com.zhxu.message.repository.ChannelConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsHandler {

    @Autowired
    private ChannelConfigRepository channelConfigRepository;

    @Autowired
    private SmsSender smsSender;

    public void handle(SmsMessage msg) {

        ChannelConfig config = channelConfigRepository.findByAreaIdAndChannelType(msg.getAreaId(), ChannelType.SMS.getName());
        SmsConfig smsConfig = JSON.parseObject(config.getContent(), SmsConfig.class);

        if (smsConfig == null) {
            return;
        }

        SmsParam param = SmsParam.builder()
                .url(smsConfig.getSmsSendUrl())
                .sign(smsConfig.getSign())
                .organizationName(smsConfig.getOrganizationName())
                .authorizeUrl(smsConfig.getAuthorizeUrl())
                .authorizeName(smsConfig.getAuthorizeUserName())
                .authorizePassword(smsConfig.getAuthorizeUserPassword())
                .content(msg.getContent())
                .mobiles(msg.getMobiles())
                .build();

        smsSender.send(param);
    }
}
