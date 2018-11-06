package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitAgriculturPark;
import com.hyt.server.mapper.base.IUnitAgriculturParkMapper;
import com.hyt.server.service.base.IUnitAgriculturParkService;
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
@Service("unitAgriculturParkService")
public  class UnitAgriculturParkServiceImpl extends AbstractService<UnitAgriculturPark> implements IUnitAgriculturParkService {

    @Autowired
    private IUnitAgriculturParkMapper unitAgriculturParkMapper;

    @Override
    public PageInfo<UnitAgriculturPark> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitAgriculturPark> areaList = this.unitAgriculturParkMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
