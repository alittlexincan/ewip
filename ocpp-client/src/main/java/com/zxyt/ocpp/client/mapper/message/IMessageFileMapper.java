package com.zxyt.ocpp.client.mapper.message;

import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.message.MessageFile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageFileMapper")
public interface IMessageFileMapper extends IBaseMapper<MessageFile> {

    int insertBatch(List<MessageFile> list);

}
