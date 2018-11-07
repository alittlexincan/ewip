package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.DisasterType;
import com.hyt.server.mapper.base.IDisasterTypeMapper;
import com.hyt.server.service.base.IDisasterTypeService;
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
@Service("disasterTypeService")
public  class DisasterTypeServiceImpl extends AbstractService<DisasterType> implements IDisasterTypeService {

    @Autowired
    private IDisasterTypeMapper disasterTypeMapper;

    @Override
    public PageInfo<DisasterType> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<DisasterType> areaList = this.disasterTypeMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<DisasterType> selectList(Map<String, Object> map){
        return this.disasterTypeMapper.findAll(map);
    }
}
