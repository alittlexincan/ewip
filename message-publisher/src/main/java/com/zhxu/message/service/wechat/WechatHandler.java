package com.zhxu.message.service.wechat;

import com.zhxu.message.MsgHandler;
import com.zhxu.model.message.Message;
import com.zhxu.message.repository.ChannelConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatHandler implements MsgHandler {

    @Autowired
    private ChannelConfigRepository channelConfigRepository;

    @Autowired
    private WechatSender wechatSender;

    @Override
    public void handle(Message msg) {

    }
}
