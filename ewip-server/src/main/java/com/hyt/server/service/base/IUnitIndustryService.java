package com.hyt.server.service.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.base.UnitIndustry;

import java.util.List;
import java.util.Map;

public interface IUnitIndustryService extends IBaseService<UnitIndustry>{

        PageInfo<UnitIndustry> selectAll(Map<String, Object> map);

        List<UnitIndustry> selectList(Map<String, Object> map);

}
