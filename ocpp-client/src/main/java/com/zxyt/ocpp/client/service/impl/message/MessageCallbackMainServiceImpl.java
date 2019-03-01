package com.zxyt.ocpp.client.service.impl.message;

import com.zxyt.ocpp.client.config.common.universal.AbstractService;
import com.zxyt.ocpp.client.entity.message.Message;
import com.zxyt.ocpp.client.entity.message.MessageCallbackMain;
import com.zxyt.ocpp.client.mapper.message.IMessageCallbackMainMapper;
import com.zxyt.ocpp.client.mapper.sys.IChannelConfigMapper;
import com.zxyt.ocpp.client.service.message.IMessageCallbackMainService;
import com.zxyt.ocpp.client.service.message.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("messageCallbackMainService")
public class MessageCallbackMainServiceImpl extends AbstractService<MessageCallbackMain> implements IMessageCallbackMainService {
    @Autowired
    private IMessageCallbackMainMapper messageCallbackMainMapper;
}
