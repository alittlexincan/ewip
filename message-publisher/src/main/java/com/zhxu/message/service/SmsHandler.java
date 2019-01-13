package com.zhxu.message.service;

import com.zhxu.message.MsgHandler;
import com.zhxu.message.entity.ChannelConfig;
import com.zhxu.message.modal.ChannelType;
import com.zhxu.message.modal.Message;
import com.zhxu.message.modal.SmsParam;
import com.zhxu.message.repository.ChannelConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("smsHandler")
public class SmsHandler implements MsgHandler {

    @Autowired
    private ChannelConfigRepository channelConfigRepository;

    @Qualifier("smsSender")
    private SmsSender smsSender;

    @Override
    public void handle(Message msg) {

        ChannelConfig config = channelConfigRepository.findByAreaIdAndChannelType(msg.getArea().getId(), ChannelType.SMS.getType()).get();

        List<String> mobiles = new ArrayList();

        Arrays.stream(msg.getGroups()).forEach(group -> {
            Arrays.stream(group.getUsers()).forEach(user -> {
                mobiles.add(user.getMobile());
            });
        });

        SmsParam param = SmsParam.builder()
                .url("")
                .mobiles(mobiles)
                .build();

        smsSender.send(param);
    }
}
