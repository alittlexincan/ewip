package com.hyt.server.service.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.message.MessageMonitor;

import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description: 一键发布接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IMessageMonitorService  extends IBaseService<MessageMonitor> {


    /**
     * 统计一键发布列表信息
     * @param map
     * @return
     */
    PageInfo<MessageMonitor> findMessageMonitor(Map<String, Object> map);

    /**
     * 根据ID查询一键发布信息（饼图）
     * @param map
     * @return
     */
    JSONArray findMessageTypeTotal(Map<String, Object> map);

    /**
     * 根据一键发布渠道进行统计（柱状图）
     * @param map
     * @return
     */
    JSONObject findMessageChannelTotal(Map<String, Object> map);

}
