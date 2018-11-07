package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.FacilityPublish;
import com.hyt.server.mapper.base.IFacilityPublishMapper;
import com.hyt.server.service.base.IFacilityPublishService;
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
@Service("facilityPublishService")
public  class FacilityPublishServiceImpl extends AbstractService<FacilityPublish> implements IFacilityPublishService {

    @Autowired
    private IFacilityPublishMapper facilityPublishMapper;

    @Override
    public PageInfo<FacilityPublish> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<FacilityPublish> areaList = this.facilityPublishMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<FacilityPublish> selectList(Map<String, Object> map){
        return this.facilityPublishMapper.findAll(map);
    }
}
