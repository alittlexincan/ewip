package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 策略配置信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("strategy")
public class StrategyController {

    @Autowired
    private IStrategyService strategyService;

    /**
     * 添加策略配置信息
     * @param map
     * @return
     */
    @PostMapping("/insert")
    public JSONObject insert(@RequestParam Map<String,Object> map){
        return this.strategyService.insert(map);
    }

    /**
     * 修改策略配置信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    public JSONObject update(@RequestParam Map<String,Object> map){
        return this.strategyService.update(map);
    }

    /**
     * 根据策略配置ID删除策略配置信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public JSONObject deleteById(@PathVariable(value = "id") String id){
        return this.strategyService.deleteById(id);
    }

    /**
     * 批量删除策略配置信息
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public JSONObject deleteBatch(@RequestParam(value = "id") String id){
        return this.strategyService.deleteBatch(id);
    }

    /**
     * 根据策略配置ID查询策略配置信息
     * @param id
     * @return
     */
    @PostMapping("/select/{id}")
    public JSONObject selectById(@PathVariable(value = "id") String id){
        return this.strategyService.selectById(id);
    }


    /**
     * 分页查询策略配置信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    public JSONObject selectAll(@RequestParam Map<String,Object> map){
        return this.strategyService.selectAll(map);
    }

}
