package com.hyt.client.service.sys;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 策略配置信息接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("strategyService")
@FeignClient("EWIP-SERVER")
public interface IStrategyService {

    /**
     * 添加策略配置信息
     * @param map
     * @return
     */
    @PostMapping("/strategy/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);

    /**
     * 修改策略配置信息
     * @param map
     * @return
     */
    @PostMapping("/strategy/update")
    JSONObject update(@RequestParam Map<String, Object> map);

    /**
     * 根据策略配置ID删除策略配置信息
     * @param id
     * @return
     */
    @DeleteMapping("/strategy/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);

    /**
     * 批量删除策略配置信息
     * @param id
     * @return
     */
    @PostMapping("/strategy/delete")
    JSONObject deleteBatch(@RequestParam(value = "id") String id);

    /**
     * 根据策略配置ID查询策略配置信息
     * @param id
     * @return
     */
    @PostMapping("/strategy/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id);


    /**
     * 分页查询策略配置信息
     * @param map
     * @return
     */
    @GetMapping("/strategy/select")
    JSONObject selectAll(@RequestParam Map<String, Object> map);

    /**
     * 多条件查询策略配置信息
     * @param map
     * @return
     */
    @GetMapping("/strategy/config")
    JSONObject selectConfig(@RequestParam Map<String, Object> map);

}
