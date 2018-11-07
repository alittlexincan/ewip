package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitMarket;
import com.hyt.server.mapper.base.IUnitMarketMapper;
import com.hyt.server.service.base.IUnitMarketService;
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
@Service("unitMarketService")
public  class UnitMarketServiceImpl extends AbstractService<UnitMarket> implements IUnitMarketService {

    @Autowired
    private IUnitMarketMapper unitMarketMapper;

    @Override
    public PageInfo<UnitMarket> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitMarket> areaList = this.unitMarketMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<UnitMarket> selectList(Map<String, Object> map){
        return this.unitMarketMapper.findAll(map);
    }
}
