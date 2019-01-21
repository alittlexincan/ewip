package com.zhxu.message.service.weibo.sina;

import com.alibaba.fastjson.JSON;
import com.zhxu.message.entity.ChannelConfig;
import com.zhxu.message.model.weibo.sina.SinaWeiboMessage;
import com.zhxu.model.message.ChannelType;
import com.zhxu.message.model.weibo.sina.SinaWeiboConfig;
import com.zhxu.message.model.weibo.sina.SinaWeiboParam;
import com.zhxu.message.repository.ChannelConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinaWeiboHandler {

    @Autowired
    private ChannelConfigRepository channelConfigRepository;

    @Autowired
    private SinaWeiboSender sinaWeiBoSender;

    public void handle(SinaWeiboMessage msg) {

        ChannelConfig config = channelConfigRepository.findByAreaIdAndChannelType(msg.getAreaId(), ChannelType.WEIBO.getName());
        SinaWeiboConfig sinaWeiboConfig = JSON.parseObject(config.getContent(), SinaWeiboConfig.class);

        if (sinaWeiboConfig == null) {
            return;
        }

        SinaWeiboParam param = SinaWeiboParam.builder()
                .sinaSendUrl(sinaWeiboConfig.getSinaSendUrl())
                .access_token(sinaWeiboConfig.getAccess_token())
                .content(msg.getContent())
                .safeUrl(sinaWeiboConfig.getSafeUrl())
                .build();

        sinaWeiBoSender.sendSinaWeiBo(param);
    }
}
