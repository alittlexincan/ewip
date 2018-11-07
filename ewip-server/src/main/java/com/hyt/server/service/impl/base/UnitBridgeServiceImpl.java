package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitBridge;
import com.hyt.server.mapper.base.IUnitBridgeMapper;
import com.hyt.server.service.base.IUnitBridgeService;
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
@Service("unitBridgeService")
public  class UnitBridgeServiceImpl extends AbstractService<UnitBridge> implements IUnitBridgeService {

    @Autowired
    private IUnitBridgeMapper unitBridgeMapper;

    @Override
    public PageInfo<UnitBridge> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitBridge> areaList = this.unitBridgeMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
