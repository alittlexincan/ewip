package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitReservoir;
import com.hyt.server.mapper.base.IUnitReservoirMapper;
import com.hyt.server.service.base.IUnitReservoirService;
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
@Service("unitReservoirService")
public  class UnitReservoirServiceImpl extends AbstractService<UnitReservoir> implements IUnitReservoirService {

    @Autowired
    private IUnitReservoirMapper unitReservoirMapper;

    @Override
    public PageInfo<UnitReservoir> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitReservoir> areaList = this.unitReservoirMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
