package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.DisasterRoute;
import com.hyt.server.mapper.base.IDisasterRouteMapper;
import com.hyt.server.service.base.IDisasterRouteService;
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
@Service("disasterRouteService")
public  class DisasterRouteServiceImpl extends AbstractService<DisasterRoute> implements IDisasterRouteService {

    @Autowired
    private IDisasterRouteMapper disasterRouteMapper;

    @Override
    public PageInfo<DisasterRoute> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<DisasterRoute> areaList = this.disasterRouteMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
