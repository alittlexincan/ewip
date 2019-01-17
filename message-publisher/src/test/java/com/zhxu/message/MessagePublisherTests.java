package com.zhxu.message;

import com.zhxu.message.service.email.EmailHandler;
import com.zhxu.message.service.sinaweibo.SinaWeiBoHandler;
import com.zhxu.message.service.sms.SmsHandler;
import com.zhxu.model.message.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessagePublisher.class)
public class MessagePublisherTests {

    @Autowired
    private SmsHandler smsHandler;

    @Autowired
    private SinaWeiBoHandler sinaWeiBoHandler;

    @Autowired
    private EmailHandler emailHandler;

    @Test
    public void testSend() {
        Message message = new Message();
        Area area = new Area();
        Channel channel = new Channel();
        Group group = new Group();
        User user = new User();

        //user.setMobile("18301485575");
        user.setEmail("1103526173@qq.com");
        List<User> users = new ArrayList<>();
        users.add(user);

        group.setUsers(users);
        List<Group> groups = new ArrayList<>();
        groups.add(group);

        message.setGroups(groups);

        area.setId("52db5b81970911e8a5ed8cec4b81c244");
        channel.setType(ChannelType.EMAIL);

        message.setArea(area);
        message.setChannel(channel);
        message.setTitle("测试信息");
        message.setContent("這是測試信息。收到请忽略！");

        emailHandler.handle(message);
//        sinaWeiBoHandler.handle(message);
    }
}
