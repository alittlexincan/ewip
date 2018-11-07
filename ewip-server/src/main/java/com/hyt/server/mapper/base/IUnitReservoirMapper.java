package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitReservoir;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitReservoirMapper")
public interface IUnitReservoirMapper extends IBaseMapper<UnitReservoir> {
    /**
     * 分页查询水库信息
     * @param map
     * @return
     */
    List<UnitReservoir> findAll(Map<String, Object> map);
}