package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.FacilityShelter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("facilityShelterMapper")
public interface IFacilityShelterMapper extends IBaseMapper<FacilityShelter> {
    /**
     * 分页查询应急避难所信息
     * @param map
     * @return
     */
    List<FacilityShelter> findAll(Map<String, Object> map);
}