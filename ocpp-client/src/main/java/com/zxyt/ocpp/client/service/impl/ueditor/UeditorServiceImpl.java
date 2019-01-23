package com.zxyt.ocpp.client.service.impl.ueditor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxyt.ocpp.client.config.common.page.MybatisPage;
import com.zxyt.ocpp.client.config.common.universal.AbstractService;
import com.zxyt.ocpp.client.entity.sys.Area;
import com.zxyt.ocpp.client.entity.sys.ChannelConfig;
import com.zxyt.ocpp.client.entity.ueditor.Ueditor;
import com.zxyt.ocpp.client.mapper.ueditor.IUeditorMapper;
import com.zxyt.ocpp.client.service.ueditor.IUeditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ueditorService")
public class UeditorServiceImpl extends AbstractService<Ueditor> implements IUeditorService {

    @Autowired
    private IUeditorMapper ueditorMapper;

    @Override
    public int insert(Map<String, Object> map) throws Exception {
        JSONObject param = new JSONObject(map);
        Ueditor ueditor = JSON.parseObject(param.toJSONString(), new TypeReference<Ueditor>() {});
        return this.ueditorMapper.insert(ueditor);
    }

    @Override
    public PageInfo<Ueditor> selectAll(Map<String, Object> map) {

        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Ueditor> list = this.ueditorMapper.findAll(map);
        return new PageInfo<>(list);
    }
}
