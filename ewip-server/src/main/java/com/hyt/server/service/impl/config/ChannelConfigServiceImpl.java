package com.hyt.server.service.impl.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.config.ChannelConfig;
import com.hyt.server.mapper.config.IChannelConfigMapper;
import com.hyt.server.service.config.IChannelConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: XincanJiang
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("channelConfigService")
public class ChannelConfigServiceImpl extends AbstractService<ChannelConfig> implements IChannelConfigService {

    @Autowired
    private IChannelConfigMapper channelConfigMapper;

    @Override
    public PageInfo<ChannelConfig> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<ChannelConfig> areaList = this.channelConfigMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    @Transactional
    public int insert(Map<String, Object> map) {
        JSONObject param = new JSONObject();
        param.put("content",new JSONObject(map).toJSONString());
        param.put("areaId", map.get("areaId"));
        param.put("organizationId", map.get("organizationId"));
        param.put("channelCode", map.get("channelCode"));
        param.put("createTime", new Date());
        ChannelConfig channelConfig = JSON.parseObject(param.toJSONString(), new TypeReference<ChannelConfig>() {});
        this.channelConfigMapper.deleteByType(map);
        return this.channelConfigMapper.insert(channelConfig);
    }

    @Override
    public List<ChannelConfig> selectByType(Map<String, Object> map) {
        return this.channelConfigMapper.findAll(map);
    }
}
