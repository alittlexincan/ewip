package com.hyt.server.service.impl.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.sys.AlarmThreshold;
import com.hyt.server.mapper.sys.IAlarmThresholdMapper;
import com.hyt.server.service.sys.IAlarmThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("alarmThresholdService")
public class AlarmThresholdServiceImpl extends AbstractService<AlarmThreshold> implements IAlarmThresholdService {

    @Autowired
    private IAlarmThresholdMapper alarmThresholdMapper;

    @Override
    public PageInfo<AlarmThreshold> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<AlarmThreshold> list = this.alarmThresholdMapper.findAll(map);
        return new PageInfo<>(list);
    }

    @Override
    public AlarmThreshold selectById(String id){
        return this.alarmThresholdMapper.selectById(id);
    }

}
