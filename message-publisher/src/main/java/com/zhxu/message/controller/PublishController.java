package com.zhxu.message.controller;


import com.zhxu.message.MsgDispatcher;
import com.zhxu.message.modal.Message;
import com.zhxu.message.modal.PubInfo;
import com.zhxu.message.MsgHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Controller
@RequestMapping("/publish")
public class PublishController implements MsgDispatcher{

    @Qualifier("smsHandler")
    MsgHandler smsHandler;

    @Qualifier("wechatHandler")
    MsgHandler wechatHandler;

    @PostMapping("/")
    public void publish(@RequestBody PubInfo info, @RequestParam MultipartFile[] files){

        dispatch(info.getMessages());

    }

    @Override
    public void dispatch(Message[] messages) {
        Arrays.stream(messages).forEach(message -> {
            switch (message.getChannel().getType()) {
                case SMS: smsHandler.handle(message); break;
                case WECHAT: wechatHandler.handle(message); break;
            }
        });
    }
}
