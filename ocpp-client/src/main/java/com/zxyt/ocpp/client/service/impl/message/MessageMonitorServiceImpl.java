package com.zxyt.ocpp.client.service.impl.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xincan.utils.disaster.MsgTypeUtil;
import com.zxyt.ocpp.client.config.common.page.MybatisPage;
import com.zxyt.ocpp.client.config.common.universal.AbstractService;
import com.zxyt.ocpp.client.entity.message.MessageMonitor;
import com.zxyt.ocpp.client.mapper.message.IMessageMonitorMapper;
import com.zxyt.ocpp.client.service.message.IMessageMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("messageMonitorService")
public class MessageMonitorServiceImpl extends AbstractService<MessageMonitor> implements IMessageMonitorService {

    @Autowired
    private IMessageMonitorMapper messageMonitorMapper;


    @Override
    public PageInfo<MessageMonitor> findMessageMonitor(Map<String, Object> map){
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<MessageMonitor> areaList = this.messageMonitorMapper.findMessageMonitor(map);
        return new PageInfo<>(areaList);
    }

    /**
     * 根据ID查询一键发布信息
     *
     * @param map
     * @return
     */
    @Override
    public JSONArray findMessageTypeTotal(Map<String, Object> map) {
        List<MessageMonitor> list = this.messageMonitorMapper.findMessageTypeTotal(map);
        if(list.size()<=0) return new JSONArray();
        list.forEach(mes -> {
            mes.setName(MsgTypeUtil.parseOneType(mes.getType()));
        });
        String str = JSON.toJSONString(list);
        return JSONArray.parseArray(str);
    }

    /**
     * 根据一键发布渠道进行统计（柱状图）
     *
     * @param map
     * @return
     */
    @Override
    public JSONObject findMessageChannelTotal(Map<String, Object> map) {
        List<MessageMonitor> list = this.messageMonitorMapper.findMessageChannelTotal(map);
        if(list.size() <= 0) return new JSONObject();
        JSONObject result = new JSONObject();
        JSONArray titleArray = new JSONArray();
        JSONArray totalArray = new JSONArray();
        JSONArray successArray = new JSONArray();
        list.forEach(mm ->{
            titleArray.add(mm.getChannelName());
            totalArray.add(mm.getTotal());
            successArray.add(mm.getSuccess());
        });
        result.put("title", titleArray);
        result.put("total", totalArray);
        result.put("success", successArray);
        return result;
    }


}
