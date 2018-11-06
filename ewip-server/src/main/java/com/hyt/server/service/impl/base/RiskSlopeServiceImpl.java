package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.RiskSlope;
import com.hyt.server.mapper.base.IRiskSlopeMapper;
import com.hyt.server.service.base.IRiskSlopeService;
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
@Service("riskSlopeService")
public  class RiskSlopeServiceImpl extends AbstractService<RiskSlope> implements IRiskSlopeService {

    @Autowired
    private IRiskSlopeMapper riskSlopeMapper;

    @Override
    public PageInfo<RiskSlope> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<RiskSlope> areaList = this.riskSlopeMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
