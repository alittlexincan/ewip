package com.hyt.client.service.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName ICimissConfigService
 * @Description 调用远程接口获取Cimiss配置
 * @Author Xincan
 * @Version 1.0
 **/
@Service("cimissConfigService")
@FeignClient("EWIP-SERVER")
public interface ICimissConfigService {

    @GetMapping("/cimissConfig/getRequestUrl")
    JSONObject getRequestUrl(@RequestParam("areaId") String areaId);

    /**
     * 添加Cimiss信息
     * @param map
     * @return
     */
    @PostMapping("/cimissConfig/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);

    /**
     * 修改Cimiss信息
     * @param map
     * @return
     */
    @PostMapping("/cimissConfig/update")
    JSONObject update(@RequestParam Map<String, Object> map);

    /**
     * 根据id删除Cimiss信息
     * @param id
     * @return
     */
    @DeleteMapping("/cimissConfig/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);

    /**
     * 根据ids批量删除Cimiss信息
     * @param id
     * @return
     */
    @PostMapping("/cimissConfig/delete")
    JSONObject deleteBatch(@RequestParam("id") String id);

    /**
     * 根据id查询Cimiss详细信息
     * @param id
     * @return
     */
    @GetMapping("/cimissConfig/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id);

    /**
     * 分页查询Cimiss信息
     * @param map
     * @return
     */
    @GetMapping("/cimissConfig/select")
    JSONObject selectAll(@RequestParam Map<String, Object> map);
}
