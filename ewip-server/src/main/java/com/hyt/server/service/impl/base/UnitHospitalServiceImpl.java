package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitHospital;
import com.hyt.server.mapper.base.IUnitHospitalMapper;
import com.hyt.server.service.base.IUnitHospitalService;
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
@Service("unitHospitalService")
public  class UnitHospitalServiceImpl extends AbstractService<UnitHospital> implements IUnitHospitalService {

    @Autowired
    private IUnitHospitalMapper unitHospitalMapper;

    @Override
    public PageInfo<UnitHospital> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitHospital> areaList = this.unitHospitalMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
