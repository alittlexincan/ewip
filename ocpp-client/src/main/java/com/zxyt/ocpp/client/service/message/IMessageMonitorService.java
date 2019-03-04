package com.zxyt.ocpp.client.service.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zxyt.ocpp.client.config.common.universal.IBaseService;
import com.zxyt.ocpp.client.entity.message.MessageMonitor;
import com.zxyt.ocpp.client.entity.sys.Area;

import java.util.Map;

public interface IMessageMonitorService extends IBaseService<MessageMonitor> {

    PageInfo<MessageMonitor> findMessageMonitor(Map<String, Object> map);


    JSONArray findMessageTypeTotal(Map<String, Object> map);

    JSONObject findMessageChannelTotal(Map<String, Object> map);
}
