package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitSquare;
import com.hyt.server.mapper.base.IUnitSquareMapper;
import com.hyt.server.service.base.IUnitSquareService;
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
@Service("unitSquareService")
public  class UnitSquareServiceImpl extends AbstractService<UnitSquare> implements IUnitSquareService {

    @Autowired
    private IUnitSquareMapper unitSquareMapper;

    @Override
    public PageInfo<UnitSquare> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitSquare> areaList = this.unitSquareMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
