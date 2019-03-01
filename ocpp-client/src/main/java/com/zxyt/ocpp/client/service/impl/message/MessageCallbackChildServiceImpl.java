package com.zxyt.ocpp.client.service.impl.message;

import com.zxyt.ocpp.client.config.common.universal.AbstractService;
import com.zxyt.ocpp.client.entity.message.MessageCallbackChild;
import com.zxyt.ocpp.client.entity.message.MessageCallbackMain;
import com.zxyt.ocpp.client.mapper.message.IMessageCallbackChildMapper;
import com.zxyt.ocpp.client.mapper.message.IMessageCallbackMainMapper;
import com.zxyt.ocpp.client.service.message.IMessageCallbackChildService;
import com.zxyt.ocpp.client.service.message.IMessageCallbackMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("messageCallbackChildService")
public class MessageCallbackChildServiceImpl extends AbstractService<MessageCallbackChild> implements IMessageCallbackChildService {
    @Autowired
    private IMessageCallbackChildMapper messageCallbackChildMapper;
}
