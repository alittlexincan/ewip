package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.RiskMountain;
import com.hyt.server.mapper.base.IRiskMountainMapper;
import com.hyt.server.service.base.IRiskMountainService;
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
@Service("riskMountainService")
public  class RiskMountainServiceImpl extends AbstractService<RiskMountain> implements IRiskMountainService {

    @Autowired
    private IRiskMountainMapper riskMountainMapper;

    @Override
    public PageInfo<RiskMountain> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<RiskMountain> areaList = this.riskMountainMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
