package com.hyt.server.service.impl.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.base.DisasterHistory;
import com.hyt.server.mapper.base.IDisasterHistoryMapper;
import com.hyt.server.service.base.IDisasterHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:29 2018-11-1
 * @Modified By:
 */
@Service("disasterHistoryService")
public  class DisasterHistoryServiceImpl extends AbstractService<DisasterHistory> implements IDisasterHistoryService {

    @Autowired
    private IDisasterHistoryMapper disasterHistoryMapper;

    @Override
    public PageInfo<DisasterHistory> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<DisasterHistory> areaList = this.disasterHistoryMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<DisasterHistory> selectList(Map<String, Object> map){
        return this.disasterHistoryMapper.findAll(map);
    }
}
