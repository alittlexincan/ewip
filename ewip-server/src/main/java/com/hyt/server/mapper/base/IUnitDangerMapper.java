package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitDanger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitDangerMapper")
public interface IUnitDangerMapper extends IBaseMapper<UnitDanger> {
    /**
     * 分页查询危险品场所信息
     * @param map
     * @return
     */
    List<UnitDanger> findAll(Map<String, Object> map);
}