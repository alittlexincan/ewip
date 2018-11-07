package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitHighway;
import com.hyt.server.mapper.base.IUnitHighwayMapper;
import com.hyt.server.service.base.IUnitHighwayService;
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
@Service("unitHighwayService")
public  class UnitHighwayServiceImpl extends AbstractService<UnitHighway> implements IUnitHighwayService {

    @Autowired
    private IUnitHighwayMapper unitHighwayMapper;

    @Override
    public PageInfo<UnitHighway> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitHighway> areaList = this.unitHighwayMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
