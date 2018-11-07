package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitHospital;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitHospitalMapper")
public interface IUnitHospitalMapper extends IBaseMapper<UnitHospital> {
    /**
     * 分页查询医院信息
     * @param map
     * @return
     */
    List<UnitHospital> findAll(Map<String, Object> map);
}