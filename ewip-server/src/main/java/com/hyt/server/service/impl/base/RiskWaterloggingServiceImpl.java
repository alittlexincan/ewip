package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.RiskWaterlogging;
import com.hyt.server.mapper.base.IRiskWaterloggingMapper;
import com.hyt.server.service.base.IRiskWaterloggingService;
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
@Service("riskWaterloggingService")
public  class RiskWaterloggingServiceImpl extends AbstractService<RiskWaterlogging> implements IRiskWaterloggingService {

    @Autowired
    private IRiskWaterloggingMapper riskWaterloggingMapper;

    @Override
    public PageInfo<RiskWaterlogging> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<RiskWaterlogging> areaList = this.riskWaterloggingMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
