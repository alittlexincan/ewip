package com.hyt.server.mapper.sys;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.sys.AlarmThreshold;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("alarmThresholdMapper")
public interface IAlarmThresholdMapper extends IBaseMapper<AlarmThreshold> {

    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<AlarmThreshold> findAll(Map<String, Object> map);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    AlarmThreshold selectById(@Param(value = "id") String id);

}
