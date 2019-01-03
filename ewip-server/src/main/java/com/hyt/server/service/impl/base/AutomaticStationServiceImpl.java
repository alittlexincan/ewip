package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.AutomaticStation;
import com.hyt.server.mapper.base.IAutomaticStationMapper;
import com.hyt.server.service.base.IAutomaticStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxv
 * @Description:
 * @Date: Created in 15:29 2018-12-24
 * @Modified By:
 */
@Service("automaticStationService")
public  class AutomaticStationServiceImpl extends AbstractService<AutomaticStation> implements IAutomaticStationService {

    @Autowired
    private IAutomaticStationMapper automaticStationMapper;

    @Override
    public PageInfo<AutomaticStation> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<AutomaticStation> areaList = this.automaticStationMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<AutomaticStation> selectList(Map<String, Object> map){
        return this.automaticStationMapper.findAll(map);
    }
}
