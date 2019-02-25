package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.RealTimeDisaster;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: lxv
 * @Description:
 * @Date: Created in 15:30 2018-11-1
 * @Modified By:
 */
@Repository("realTimeDisasterMapper")
public interface IRealTimeDisasterMapper extends IBaseMapper<RealTimeDisaster> {

    /**
     * 分页查询历史灾情信息
     * @param map
     * @return
     */
    List<RealTimeDisaster> findAll(Map<String, Object> map);

}