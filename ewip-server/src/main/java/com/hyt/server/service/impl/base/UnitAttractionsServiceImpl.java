package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitAttractions;
import com.hyt.server.mapper.base.IUnitAttractionsMapper;
import com.hyt.server.service.base.IUnitAttractionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:29 2018-11-2
 * @Modified By:
 */
@Service("unitAttractionsService")
public  class UnitAttractionsServiceImpl extends AbstractService<UnitAttractions> implements IUnitAttractionsService {

    @Autowired
    private IUnitAttractionsMapper unitAttractionsMapper;

    @Override
    public PageInfo<UnitAttractions> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitAttractions> areaList = this.unitAttractionsMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
    @Override
    public List<UnitAttractions> selectList(Map<String, Object> map){
        return this.unitAttractionsMapper.findAll(map);
    }
}
