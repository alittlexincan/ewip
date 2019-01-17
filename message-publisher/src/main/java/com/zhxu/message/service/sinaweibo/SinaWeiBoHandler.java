package com.zhxu.message.service.sinaweibo;

import com.alibaba.fastjson.JSON;
import com.zhxu.message.MsgHandler;
import com.zhxu.message.entity.ChannelConfig;
import com.zhxu.model.message.ChannelType;
import com.zhxu.model.message.Message;
import com.zhxu.message.model.sinaweibo.SinaWeiBoConfig;
import com.zhxu.message.model.sinaweibo.SinaWeiBoParam;
import com.zhxu.message.repository.ChannelConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinaWeiBoHandler implements MsgHandler {

    @Autowired
    private ChannelConfigRepository channelConfigRepository;

    @Autowired
    private SinaWeiBoSender sinaWeiBoSender;

    @Override
    public void handle(Message msg) {

        ChannelConfig config = channelConfigRepository.findByAreaIdAndChannelType(msg.getArea().getId(), ChannelType.SINA_WEIBO.getType());

        SinaWeiBoConfig sinaWeiBoConfig = JSON.parseObject(config.getContent(), SinaWeiBoConfig.class);

        SinaWeiBoParam param = SinaWeiBoParam.builder()
                .sinaSendUrl(sinaWeiBoConfig.getSinaSendUrl())
                .access_token(sinaWeiBoConfig.getAccess_token())
                .content(msg.getContent())
                .safeUrl(sinaWeiBoConfig.getSafeUrl())
                .build();
        sinaWeiBoSender.sendSinaWeiBo(param);
    }
}
