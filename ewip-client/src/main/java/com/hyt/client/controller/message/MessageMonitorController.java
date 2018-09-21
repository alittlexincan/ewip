package com.hyt.client.controller.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.message.IMessageMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 一键发布信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("/message/monitor")
public class MessageMonitorController {

    @Autowired
    private IMessageMonitorService messageMonitorService;

    /**
     * 分页查询地区信息
     * @param map
     * @return
     */
    @PostMapping("/type")
    JSONArray findMessageTypeTotal(@RequestParam Map<String,Object> map){
        JSONObject result = this.messageMonitorService.findMessageTypeTotal(map);
        return result.getJSONArray("data");
    }

    /**
     * 根据一键发布渠道进行统计（柱状图）
     * @param map
     * @return
     */
    @PostMapping("/channel")
    JSONObject findMessageChannelTotal(@RequestParam Map<String, Object> map){
        JSONObject result = this.messageMonitorService.findMessageChannelTotal(map);
        return result.getJSONObject("data");
    }

    /**
     * 统计一键发布列表信息
     * @param map
     * @return
     */
    @GetMapping("/list")
    JSONObject findMessageMonitor(@RequestParam Map<String, Object> map){
        return this.messageMonitorService.findMessageMonitor(map);
    }

    /**
     * 根据条件查询一键发布信息受众接收详情
     * @param map
     * @return
     */
    @GetMapping("/users")
    JSONObject findMessageMonitorUsers(@RequestParam Map<String, Object> map){
        return this.messageMonitorService.findMessageMonitorUsers(map);
    }

}
