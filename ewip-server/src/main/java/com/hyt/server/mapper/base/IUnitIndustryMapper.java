package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitIndustry;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository("unitIndustryMapper")
public interface IUnitIndustryMapper extends IBaseMapper<UnitIndustry> {

    List<UnitIndustry> findAll(Map<String, Object> map);
}