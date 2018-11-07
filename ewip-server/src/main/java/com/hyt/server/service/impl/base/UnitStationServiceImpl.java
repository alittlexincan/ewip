package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitStation;
import com.hyt.server.mapper.base.IUnitStationMapper;
import com.hyt.server.service.base.IUnitStationService;
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
@Service("unitStationService")
public  class UnitStationServiceImpl extends AbstractService<UnitStation> implements IUnitStationService {

    @Autowired
    private IUnitStationMapper unitStationMapper;

    @Override
    public PageInfo<UnitStation> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitStation> areaList = this.unitStationMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<UnitStation> selectList(Map<String, Object> map){
        return this.unitStationMapper.findAll(map);
    }
}
