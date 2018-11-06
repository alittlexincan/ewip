package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.FacilityOffice;
import com.hyt.server.mapper.base.IFacilityOfficeMapper;
import com.hyt.server.service.base.IFacilityOfficeService;
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
@Service("facilityOfficeService")
public  class FacilityOfficeServiceImpl extends AbstractService<FacilityOffice> implements IFacilityOfficeService {

    @Autowired
    private IFacilityOfficeMapper facilityOfficeMapper;

    @Override
    public PageInfo<FacilityOffice> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<FacilityOffice> areaList = this.facilityOfficeMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
