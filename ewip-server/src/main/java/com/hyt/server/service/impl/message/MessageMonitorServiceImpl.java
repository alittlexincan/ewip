package com.hyt.server.service.impl.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.message.MessageMonitor;
import com.hyt.server.mapper.message.IMessageMonitorMapper;
import com.hyt.server.service.message.IMessageMonitorService;
import com.xincan.utils.disaster.MsgTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 * 一键发布业务实现层
 *
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("messageMonitorService")
public class MessageMonitorServiceImpl  extends AbstractService<MessageMonitor> implements IMessageMonitorService {

    @Autowired
    private IMessageMonitorMapper messageMonitorMapper;

    /**
     * 统计一键发布列表信息
     * @param map
     * @return
     */
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
