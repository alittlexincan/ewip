package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitStation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitStationMapper")
public interface IUnitStationMapper extends IBaseMapper<UnitStation> {
    /**
     * 分页查询车站信息
     * @param map
     * @return
     */
    List<UnitStation> findAll(Map<String, Object> map);
}