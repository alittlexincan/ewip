package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.RiskDepression;
import com.hyt.server.mapper.base.IRiskDepressionMapper;
import com.hyt.server.service.base.IRiskDepressionService;
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
@Service("riskDepressionService")
public  class RiskDepressionServiceImpl extends AbstractService<RiskDepression> implements IRiskDepressionService {

    @Autowired
    private IRiskDepressionMapper riskDepressionMapper;

    @Override
    public PageInfo<RiskDepression> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<RiskDepression> areaList = this.riskDepressionMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<RiskDepression> selectList(Map<String, Object> map){
        return this.riskDepressionMapper.findAll(map);
    }
}
