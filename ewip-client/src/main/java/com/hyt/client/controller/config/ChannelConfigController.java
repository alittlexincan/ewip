package com.hyt.client.controller.config;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.config.IChannelConfigService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @Author: lixiaowei
 * @Description: 渠道配置管理
 * @Date: Created in 18:04 2019-01-28
 * @Modified By:
 */
@RestController
@RequestMapping("channel/config")
public class ChannelConfigController {

    /**
     * 注入渠道配置管理接口
     */
    @Autowired
    private IChannelConfigService channelConfigService;


    /**
     * 分页查询信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("empAreaId", employee.getString("areaId"));
        return this.channelConfigService.selectAll(map);
    }


    /**
     * 添加信息
     *
     * @param map
     * @return
     */
    @PostMapping("/insert")
    JSONObject insert(@RequestParam Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        JSONObject json = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("areaId", json.getString("areaId"));
        map.put("organizationId", json.getString("organizationId"));
        return this.channelConfigService.insert(map);
    }



    @PostMapping("/select/type")
    JSONObject selectByType(@RequestParam Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("empAreaId", employee.getString("areaId"));
        return this.channelConfigService.selectByType(map);
    }

}
