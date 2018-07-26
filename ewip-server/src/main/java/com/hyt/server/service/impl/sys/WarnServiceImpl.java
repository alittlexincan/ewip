package com.hyt.server.service.impl.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.sys.Warn;
import com.hyt.server.mapper.sys.IWarnMapper;
import com.hyt.server.service.sys.IWarnService;
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
@Service("earnService")
public class WarnServiceImpl extends AbstractService<Warn> implements IWarnService {

    @Autowired
    private IWarnMapper warnMapper;

    @Override
    public PageInfo<Warn> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Warn> areaList = this.warnMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public Warn selectById(String id){
        return this.warnMapper.selectById(id);
    }

}
