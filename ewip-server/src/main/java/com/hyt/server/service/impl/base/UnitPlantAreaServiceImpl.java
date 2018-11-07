package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitPlantArea;
import com.hyt.server.mapper.base.IUnitPlantAreaMapper;
import com.hyt.server.service.base.IUnitPlantAreaService;
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
@Service("unitPlantAreaService")
public  class UnitPlantAreaServiceImpl extends AbstractService<UnitPlantArea> implements IUnitPlantAreaService {

    @Autowired
    private IUnitPlantAreaMapper unitPlantAreaMapper;

    @Override
    public PageInfo<UnitPlantArea> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitPlantArea> areaList = this.unitPlantAreaMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
