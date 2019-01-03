package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.AutomaticStation;
import com.hyt.server.entity.base.UnitSchool;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxv
 * @Description:
 * @Date: Created in 15:30 2018-12-24
 * @Modified By:
 */
@Repository("automaticStationMapper")
public interface IAutomaticStationMapper extends IBaseMapper<AutomaticStation> {
    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<AutomaticStation> findAll(Map<String, Object> map);
}