package com.zhxu.message.service;

import com.zhxu.message.MsgHandler;
import com.zhxu.message.modal.Message;
import org.springframework.stereotype.Service;

@Service("wechatHandler")
public class WechatHandler implements MsgHandler {
    @Override
    public void handle(Message msg) {

    }
}
