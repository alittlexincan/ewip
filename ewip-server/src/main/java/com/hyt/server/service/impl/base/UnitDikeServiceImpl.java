package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitDike;
import com.hyt.server.mapper.base.IUnitDikeMapper;
import com.hyt.server.service.base.IUnitDikeService;
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
@Service("unitDikeService")
public  class UnitDikeServiceImpl extends AbstractService<UnitDike> implements IUnitDikeService {

    @Autowired
    private IUnitDikeMapper unitDikeMapper;

    @Override
    public PageInfo<UnitDike> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitDike> areaList = this.unitDikeMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<UnitDike> selectList(Map<String, Object> map){
        return this.unitDikeMapper.findAll(map);
    }
}
