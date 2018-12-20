package com.hyt.client.service.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 地区信息接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("userService")
@FeignClient("EWIP-SERVER")
public interface IUserService {

    /**
     * 添加受众信息
     * @param map
     * @return
     */
    @PostMapping("/user/insert")
    JSONObject insert(@RequestParam Map<String,Object> map);

    /**
     * 修改受众信息
     * @param map
     * @return
     */
    @PostMapping("/user/update")
    JSONObject update(@RequestParam Map<String,Object> map);

    /**
     * 根据受众ID删除受众信息
     * @param id
     * @return
     */
    @DeleteMapping("/user/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);

    /**
     * 批量删除受众信息
     * @param id
     * @return
     */
    @PostMapping("/user/delete")
    JSONObject deleteBatch(@RequestParam(value = "id") String id);

    /**
     * 根据受众ID查询受众信息
     * @param id
     * @return
     */
    @PostMapping("/user/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id);


    /**
     * 分页查询受众信息
     * @param map
     * @return
     */
    @GetMapping("/user/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map);

    /**
     * 分页查询受众信息
     * @param map
     * @return
     */
    @GetMapping("/user/list")
    JSONObject selectList(@RequestParam Map<String,Object> map);

    /**
     * 查询受众的详细信息
     * @param map
     * @return
     */
    @PostMapping("/user/userDetails")
    JSONObject userDetails(@RequestParam Map<String,Object> map);

    /**
     * 下载模板
     * @param map
     * @return
     */
    @PostMapping("/user/downModel")
    JSONObject downModel(@RequestParam Map<String,Object> map);

    /**
     * 数据导入
     * @return
     */
    @PostMapping("/user/importData")
    JSONObject importData(@RequestParam Map<String,Object> map,@RequestBody List<Map<String, Object>> list);

}
