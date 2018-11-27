package com.hyt.server.service.impl.standard;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.standard.Standard;
import com.hyt.server.mapper.standard.IStandardMapper;
import com.hyt.server.service.standard.IStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: lixiaowei
 * @Description:
 * @Modified By:
 */
@Service("standardService")
public  class StandardServiceImpl extends AbstractService<Standard> implements IStandardService {

    @Autowired
    private IStandardMapper standardMapper;

    @Override
    public PageInfo<Standard> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Standard> standardList = this.standardMapper.findAll(map);
        return new PageInfo<>(standardList);
    }

    @Override
    public List<Standard> selectList(Map<String, Object> map){
        return this.standardMapper.findAll(map);
    }
}
