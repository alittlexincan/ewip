package com.zxyt.ocpp.client.service.impl.sys;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxyt.ocpp.client.config.common.page.MybatisPage;
import com.zxyt.ocpp.client.config.common.universal.AbstractService;
import com.zxyt.ocpp.client.entity.sys.Area;
import com.zxyt.ocpp.client.entity.sys.WarnConfig;
import com.zxyt.ocpp.client.mapper.sys.IWarnConfigMapper;
import com.zxyt.ocpp.client.service.sys.IWarnConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("warnConfigService")
public class WarnConfigServiceImpl extends AbstractService<WarnConfig> implements IWarnConfigService {

    @Autowired
    private IWarnConfigMapper warnConfigMapper;

    @Override
    public PageInfo<WarnConfig> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<WarnConfig> list = this.warnConfigMapper.findAll(map);
        return new PageInfo<>(list);
    }

    @Override
    public WarnConfig selectConfig(Map<String, Object> map) {

        return warnConfigMapper.selectConfig(map);
    }

}
