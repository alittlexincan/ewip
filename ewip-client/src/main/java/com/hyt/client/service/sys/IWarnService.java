package com.hyt.client.service.sys;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 预警配置信息接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("warnService")
@FeignClient("EWIP-SERVER")
public interface IWarnService {

    /**
     * 添加预警配置信息
     * @param map
     * @return
     */
    @PostMapping("/warn/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);

    /**
     * 修改预警配置信息
     * @param map
     * @return
     */
    @PostMapping("/warn/update")
    JSONObject update(@RequestParam Map<String, Object> map);

    /**
     * 根据预警配置ID删除预警配置信息
     * @param id
     * @return
     */
    @DeleteMapping("/warn/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);

    /**
     * 批量删除预警配置信息
     * @param id
     * @return
     */
    @PostMapping("/warn/delete")
    JSONObject deleteBatch(@RequestParam(value = "id") String id);

    /**
     * 根据预警配置ID查询预警配置信息
     * @param id
     * @return
     */
    @PostMapping("/warn/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id);


    /**
     * 分页查询预警配置信息
     * @param map
     * @return
     */
    @GetMapping("/warn/select")
    JSONObject selectAll(@RequestParam Map<String, Object> map);

    /**
     * 分页查询预警配置信息
     * @param map
     * @return
     */
    @GetMapping("/warn/config")
    JSONObject selectConfig(@RequestParam Map<String, Object> map);

}
