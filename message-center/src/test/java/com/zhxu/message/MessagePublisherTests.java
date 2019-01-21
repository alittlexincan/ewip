package com.zhxu.message;

import com.zhxu.message.service.email.EmailHandler;
import com.zhxu.message.service.record.RecordHandler;
import com.zhxu.message.service.weibo.sina.SinaWeiboHandler;
import com.zhxu.message.service.sms.SmsHandler;
import com.zhxu.message.service.wechat.WechatHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessagePublisher.class)
public class MessagePublisherTests {

    @Autowired
    private SmsHandler smsHandler;

    @Autowired
    private SinaWeiboHandler sinaWeiBoHandler;

    @Autowired
    private EmailHandler emailHandler;

    @Autowired
    private WechatHandler wechatHandler;

    @Autowired
    private RecordHandler recordHandler;

    @Test
    public void testSend() {


        //wechatHandler.handle(message);
//        recordHandler.handle(message);
//        sinaWeiBoHandler.handle(message);
        smsHandler.handle(null);
    }
}
