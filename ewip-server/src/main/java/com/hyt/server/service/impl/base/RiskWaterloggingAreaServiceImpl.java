package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.RiskWaterloggingArea;
import com.hyt.server.mapper.base.IRiskWaterloggingAreaMapper;
import com.hyt.server.service.base.IRiskWaterloggingAreaService;
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
@Service("riskWaterloggingAreaService")
public  class RiskWaterloggingAreaServiceImpl extends AbstractService<RiskWaterloggingArea> implements IRiskWaterloggingAreaService {

    @Autowired
    private IRiskWaterloggingAreaMapper riskWaterloggingAreaMapper;

    @Override
    public PageInfo<RiskWaterloggingArea> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<RiskWaterloggingArea> areaList = this.riskWaterloggingAreaMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
