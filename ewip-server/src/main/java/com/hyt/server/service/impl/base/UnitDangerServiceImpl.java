package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitDanger;
import com.hyt.server.mapper.base.IUnitDangerMapper;
import com.hyt.server.service.base.IUnitDangerService;
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
@Service("unitDangerService")
public  class UnitDangerServiceImpl extends AbstractService<UnitDanger> implements IUnitDangerService {

    @Autowired
    private IUnitDangerMapper unitDangerMapper;

    @Override
    public PageInfo<UnitDanger> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitDanger> areaList = this.unitDangerMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
