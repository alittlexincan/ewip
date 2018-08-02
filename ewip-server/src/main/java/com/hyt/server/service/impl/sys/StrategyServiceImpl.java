package com.hyt.server.service.impl.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.sys.Strategy;
import com.hyt.server.mapper.sys.IStrategyMapper;
import com.hyt.server.service.sys.IStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("strategyService")
public class StrategyServiceImpl extends AbstractService<Strategy> implements IStrategyService {

    @Autowired
    private IStrategyMapper strategyMapper;

    @Override
    public PageInfo<Strategy> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Strategy> areaList = this.strategyMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public Strategy selectById(String id){
        return this.strategyMapper.selectById(id);
    }

    @Override
    public Strategy selectConfig(Map<String, Object> map){
        return this.strategyMapper.selectConfig(map);
    }

}
