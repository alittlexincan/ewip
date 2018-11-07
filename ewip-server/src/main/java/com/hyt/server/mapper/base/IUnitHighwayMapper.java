package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitHighway;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitHighwayMapper")
public interface IUnitHighwayMapper extends IBaseMapper<UnitHighway> {
    /**
     * 分页查询高速公路信息
     * @param map
     * @return
     */
    List<UnitHighway> findAll(Map<String, Object> map);
}