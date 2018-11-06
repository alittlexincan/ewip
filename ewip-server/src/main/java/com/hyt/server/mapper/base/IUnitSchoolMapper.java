package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitSchool;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitSchoolMapper")
public interface IUnitSchoolMapper extends IBaseMapper<UnitSchool> {
    /**
     * 分页查询学校信息
     * @param map
     * @return
     */
    List<UnitSchool> findAll(Map<String, Object> map);
}