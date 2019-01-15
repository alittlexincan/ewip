package com.zhxu.message.controller;


import com.zhxu.message.MsgDispatcher;
import com.zhxu.message.modal.Message;
import com.zhxu.message.modal.PubInfo;
import com.zhxu.message.service.sms.SmsHandler;
import com.zhxu.message.service.wechat.WechatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/publish")
public class PublishController implements MsgDispatcher{

    @Autowired
    SmsHandler smsHandler;

    @Autowired
    WechatHandler wechatHandler;

    @PostMapping("/")
    public void publish(@RequestBody PubInfo info){

        dispatch(info.getMessages());

    }

    @Override
    public void dispatch(List<Message> messages) {
        messages.forEach(message -> {
            switch (message.getChannel().getType()) {
                case SMS: smsHandler.handle(message); break;
                case WECHAT: wechatHandler.handle(message); break;
            }
        });
    }
}
