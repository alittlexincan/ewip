package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitIndustry;
import com.hyt.server.mapper.base.IUnitIndustryMapper;
import com.hyt.server.service.base.IUnitIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("unitIndustryService")
public  class UnitIndustryServiceImpl extends AbstractService<UnitIndustry> implements IUnitIndustryService {

    @Autowired
    private IUnitIndustryMapper unitIndustryMapper;

    @Override
    public PageInfo<UnitIndustry> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitIndustry> areaList = this.unitIndustryMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<UnitIndustry> selectList(Map<String, Object> map){
        return this.unitIndustryMapper.findAll(map);
    }
}
