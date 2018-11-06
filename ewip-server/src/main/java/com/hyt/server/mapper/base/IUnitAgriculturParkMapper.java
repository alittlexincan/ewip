package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitAgriculturPark;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitAgriculturParkMapper")
public interface IUnitAgriculturParkMapper extends IBaseMapper<UnitAgriculturPark> {
    /**
     * 分页查询农业园区信息
     * @param map
     * @return
     */
    List<UnitAgriculturPark> findAll(Map<String, Object> map);
}