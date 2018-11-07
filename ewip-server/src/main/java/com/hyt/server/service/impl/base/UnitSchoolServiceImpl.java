package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.UnitSchool;
import com.hyt.server.mapper.base.IUnitSchoolMapper;
import com.hyt.server.service.base.IUnitSchoolService;
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
@Service("unitSchoolService")
public  class UnitSchoolServiceImpl extends AbstractService<UnitSchool> implements IUnitSchoolService {

    @Autowired
    private IUnitSchoolMapper unitSchoolMapper;

    @Override
    public PageInfo<UnitSchool> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UnitSchool> areaList = this.unitSchoolMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<UnitSchool> selectList(Map<String, Object> map){
        return this.unitSchoolMapper.findAll(map);
    }
}
