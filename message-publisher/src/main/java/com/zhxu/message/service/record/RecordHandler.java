package com.zhxu.message.service.record;

import com.alibaba.fastjson.JSON;
import com.zhxu.message.MsgHandler;
import com.zhxu.message.entity.ChannelConfig;
import com.zhxu.message.model.record.RecordConfig;
import com.zhxu.message.model.record.RecordParam;
import com.zhxu.message.repository.ChannelConfigRepository;
import com.zhxu.model.message.ChannelType;
import com.zhxu.model.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordHandler implements MsgHandler {

    @Autowired
    private ChannelConfigRepository channelConfigRepository;

    @Autowired
    private RecordSender recordSender;

    @Override
    public void handle(Message msg) {

        ChannelConfig config = channelConfigRepository.findByAreaIdAndChannelType(msg.getArea().getId(), ChannelType.RECORD.getType());
        RecordConfig recordConfig = JSON.parseObject(config.getContent(), RecordConfig.class);

        RecordParam param = RecordParam.builder()
                .host(recordConfig.getHost())
                .user(recordConfig.getUser())
                .password(recordConfig.getPassword())
                .port(recordConfig.getPort())
                .title(msg.getTitle())
                .content(msg.getContent())
                .build();

        recordSender.send(param);

    }
}
