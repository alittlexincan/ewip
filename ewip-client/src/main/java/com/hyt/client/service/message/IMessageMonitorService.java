package com.hyt.client.service.message;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 一键发布信息接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("messageMonitorService")
@FeignClient("EWIP-SERVER")
public interface IMessageMonitorService {

    /**
     * 统计一键发布列表信息
     * @param map
     * @return
     */
    @GetMapping("/message/monitor/list")
    JSONObject findMessageMonitor(@RequestParam Map<String, Object> map);

    /**
     * 根据一键发布信息类型占比进行统计（饼图）
     * @param map
     * @return
     */
    @PostMapping("/message/monitor/type")
    JSONObject findMessageTypeTotal(@RequestParam Map<String, Object> map);

    /**
     * 根据一键发布渠道进行统计（柱状图）
     * @param map
     * @return
     */
    @PostMapping("/message/monitor/channel")
    JSONObject findMessageChannelTotal(@RequestParam Map<String, Object> map);

}
