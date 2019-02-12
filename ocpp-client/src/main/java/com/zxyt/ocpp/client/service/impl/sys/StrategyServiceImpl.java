package com.zxyt.ocpp.client.service.impl.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxyt.ocpp.client.config.common.page.MybatisPage;
import com.zxyt.ocpp.client.config.common.universal.AbstractService;
import com.zxyt.ocpp.client.entity.sys.Area;
import com.zxyt.ocpp.client.entity.sys.Strategy;
import com.zxyt.ocpp.client.mapper.sys.IStrategyMapper;
import com.zxyt.ocpp.client.service.sys.IStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("strategyService")
public class StrategyServiceImpl extends AbstractService<Strategy> implements IStrategyService {

    @Autowired
    private IStrategyMapper strategyMapper;

    @Override
    public PageInfo<Strategy> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Strategy> list = this.strategyMapper.findAll(map);
        return new PageInfo<>(list);
    }
}
