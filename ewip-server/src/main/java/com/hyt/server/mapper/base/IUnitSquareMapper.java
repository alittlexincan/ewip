package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitSquare;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitSquareMapper")
public interface IUnitSquareMapper extends IBaseMapper<UnitSquare> {
    /**
     * 分页查询广场信息
     * @param map
     * @return
     */
    List<UnitSquare> findAll(Map<String, Object> map);
}