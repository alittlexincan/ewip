package com.hyt.client.service.sys;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Service("alarmThresholdService")
@FeignClient("EWIP-SERVER")
public interface IAlarmThresholdService {

    /**
     * 添加信息
     * @param map
     * @return
     */
    @PostMapping("/alarmThreshold/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);

    /**
     * 修改信息
     * @param map
     * @return
     */
    @PostMapping("/alarmThreshold/update")
    JSONObject update(@RequestParam Map<String, Object> map);

    /**
     * 根据地区id删除信息
     * @param id
     * @return
     */
    @DeleteMapping("/alarmThreshold/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);

    /**
     * 根据ids批量删除信息
     * @param id
     * @return
     */
    @PostMapping("/alarmThreshold/delete")
    JSONObject deleteBatch(@RequestParam("id") String id);

    /**
     * 根据用户id查询详细信息
     * @param id
     * @return
     */
    @GetMapping("/alarmThreshold/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id);

    /**
     * 分页查询信息
     * @param map
     * @return
     */
    @GetMapping("/alarmThreshold/select")
    JSONObject selectAll(@RequestParam Map<String, Object> map);

}
