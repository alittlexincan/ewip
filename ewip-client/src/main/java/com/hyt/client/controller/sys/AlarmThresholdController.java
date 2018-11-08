package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IAlarmThresholdService;
import com.hyt.client.service.sys.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("alarmThreshold")
public class AlarmThresholdController {

    @Autowired
    private IAlarmThresholdService alarmThresholdService;



    /**
     * 添加信息
     * @param map
     * @return
     */
    @PostMapping("/insert")
    JSONObject insert(@RequestParam Map<String,Object> map){
        return this.alarmThresholdService.insert(map);
    }

    /**
     * 修改信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    JSONObject update(@RequestParam Map<String,Object> map){
        return this.alarmThresholdService.update(map);
    }

    /**
     * 根据地区id删除信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id){
        return this.alarmThresholdService.deleteById(id);
    }

    /**
     * 根据ids批量删除信息
     * @param id
     * @return
     */
    @PostMapping("/delete")
    JSONObject deleteBatch(@RequestParam(value = "id") String id){
        return this.alarmThresholdService.deleteBatch(id);
    }

    /**
     * 根据地区id查询信息
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id){
        return this.alarmThresholdService.selectById(id);
    }

    /**
     * 分页查询信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map){
        return this.alarmThresholdService.selectAll(map);
    }

    /**
     * 查询信息
     * @param map
     * @return
     */
    @GetMapping("/select/all")
    JSONObject select(@RequestParam Map<String,Object> map){
        return this.alarmThresholdService.select(map);
    }


}
