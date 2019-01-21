package com.zhxu.message.service.record;

import com.alibaba.fastjson.JSON;
import com.zhxu.message.entity.ChannelConfig;
import com.zhxu.message.model.record.RecordConfig;
import com.zhxu.message.model.record.RecordMessage;
import com.zhxu.message.model.record.RecordParam;
import com.zhxu.message.repository.ChannelConfigRepository;
import com.zhxu.model.message.ChannelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordHandler {

    @Autowired
    private ChannelConfigRepository channelConfigRepository;

    @Autowired
    private RecordSender recordSender;

    public void handle(RecordMessage msg) {

        ChannelConfig config = channelConfigRepository.findByAreaIdAndChannelType(msg.getAreaId(), ChannelType.RECORD.getName());
        RecordConfig recordConfig = JSON.parseObject(config.getContent(), RecordConfig.class);

        if (recordConfig == null) {
            return;
        }

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
