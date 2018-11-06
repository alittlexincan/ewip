package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.RiskGeologic;
import com.hyt.server.mapper.base.IRiskGeologicMapper;
import com.hyt.server.service.base.IRiskGeologicService;
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
@Service("riskGeologicService")
public  class RiskGeologicServiceImpl extends AbstractService<RiskGeologic> implements IRiskGeologicService {

    @Autowired
    private IRiskGeologicMapper riskGeologicMapper;

    @Override
    public PageInfo<RiskGeologic> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<RiskGeologic> areaList = this.riskGeologicMapper.findAll(map);
        return new PageInfo<>(areaList);
    }
}
