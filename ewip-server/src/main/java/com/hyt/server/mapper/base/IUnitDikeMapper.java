package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitDike;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitDikeMapper")
public interface IUnitDikeMapper extends IBaseMapper<UnitDike> {
    /**
     * 分页查询提防信息
     * @param map
     * @return
     */
    List<UnitDike> findAll(Map<String, Object> map);
}