package com.hyt.monitor.service.impl.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.monitor.config.common.page.MybatisPage;
import com.hyt.monitor.config.common.universal.AbstractService;
import com.hyt.monitor.entity.sys.Area;
import com.hyt.monitor.mapper.sys.IAreaMapper;
import com.hyt.monitor.service.sys.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: lixiaowei
 * @Description:
 * @Modified By:
 */
@Service("areaService")
public class AreaServiceImpl extends AbstractService<Area> implements IAreaService {

    @Autowired
    private IAreaMapper areaMapper;

    @Override
    public PageInfo<Area> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Area> areaList = this.areaMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public Area selectById(String id){
        return this.areaMapper.selectById(id);
    }

}
