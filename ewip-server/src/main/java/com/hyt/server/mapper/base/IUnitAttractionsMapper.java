package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitAttractions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitAttractionsMapper")
public interface IUnitAttractionsMapper extends IBaseMapper<UnitAttractions> {
    /**
     * 分页查询旅游景区信息
     * @param map
     * @return
     */
    List<UnitAttractions> findAll(Map<String, Object> map);
}