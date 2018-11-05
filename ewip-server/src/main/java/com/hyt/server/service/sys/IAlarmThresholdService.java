package com.hyt.server.service.sys;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.sys.AlarmThreshold;
import com.hyt.server.entity.sys.Area;

import java.util.Map;


public interface IAlarmThresholdService extends IBaseService<AlarmThreshold> {

    PageInfo<AlarmThreshold> selectAll(Map<String, Object> map);

    AlarmThreshold selectById(String id);

}
