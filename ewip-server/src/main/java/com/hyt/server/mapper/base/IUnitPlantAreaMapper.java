package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitPlantArea;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitPlantAreaMapper")
public interface IUnitPlantAreaMapper extends IBaseMapper<UnitPlantArea> {
    /**
     * 分页查询农作物种植区信息
     * @param map
     * @return
     */
    List<UnitPlantArea> findAll(Map<String, Object> map);
}