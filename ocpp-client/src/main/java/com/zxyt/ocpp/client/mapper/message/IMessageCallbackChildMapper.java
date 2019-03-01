package com.zxyt.ocpp.client.mapper.message;

import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.message.MessageCallbackChild;
import com.zxyt.ocpp.client.entity.message.MessageCallbackMain;
import org.springframework.stereotype.Repository;

@Repository("messageCallbackChildMapper")
public interface IMessageCallbackChildMapper extends IBaseMapper<MessageCallbackChild> {
}
