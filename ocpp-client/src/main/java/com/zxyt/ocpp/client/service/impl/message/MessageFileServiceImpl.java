package com.zxyt.ocpp.client.service.impl.message;

import com.zxyt.ocpp.client.config.common.universal.AbstractService;
import com.zxyt.ocpp.client.entity.message.Message;
import com.zxyt.ocpp.client.entity.message.MessageFile;
import com.zxyt.ocpp.client.mapper.message.IMessageFileMapper;
import com.zxyt.ocpp.client.mapper.message.IMessageMapper;
import com.zxyt.ocpp.client.service.message.IMessageFileService;
import com.zxyt.ocpp.client.service.message.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("messageFileService")
public class MessageFileServiceImpl extends AbstractService<MessageFile> implements IMessageFileService {

    @Autowired
    private IMessageFileMapper messageFileMapper;
}
