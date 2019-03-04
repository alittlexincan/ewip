package com.zxyt.ocpp.client.mapper.message;

import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.message.MessageMonitor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("messageMonitorMapper")
public interface IMessageMonitorMapper extends IBaseMapper<MessageMonitor> {


    List<MessageMonitor> findMessageMonitor(Map<String, Object> map);

    List<MessageMonitor> findMessageTypeTotal(Map<String, Object> map);

    List<MessageMonitor> findMessageChannelTotal(Map<String, Object> map);
}
