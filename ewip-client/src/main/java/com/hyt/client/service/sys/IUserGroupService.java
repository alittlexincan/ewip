package com.hyt.client.service.sys;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 群组信息接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("userGroupService")
@FeignClient("EWIP-SERVER")
public interface IUserGroupService {

    /**
     * 添加群组信息
     * @param map
     * @return
     */
    @PostMapping("/group/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);


    /**
     * 修改群组信息
     * @param map
     * @return
     */
    @PostMapping("/group/update")
    JSONObject update(@RequestParam Map<String, Object> map);

    /**
     * 根据群组ID删除群组信息
     * @param id
     * @return
     */
    @DeleteMapping("/group/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);

    /**
     * 批量删除群组信息
     * @param id
     * @return
     */
    @PostMapping("/group/delete")
    JSONObject deleteBatch(@RequestParam(value = "id") String id);

    /**
     * 根据群组ID查询受众信息
     * @param id
     * @return
     */
    @PostMapping("/group/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id);

    /**
     * 分页查询群组信息
     * @param map
     * @return
     */
    @GetMapping("/group/select")
    JSONObject selectAll(@RequestParam Map<String, Object> map);
    /**
     * 查询群组信息
     * @param map
     * @return
     */
    @PostMapping("/group/selectGroup")
    JSONObject selectGroup(@RequestParam Map<String,Object> map);

    /**
     * 下载模板
     * @param map
     * @return
     */
    @PostMapping("/group/downModel")
    JSONObject downModel(@RequestParam Map<String,Object> map);

    /**
     * 数据导入
     * @return
     */
    @PostMapping("/group/importData")
    JSONObject importData(@RequestParam Map<String,Object> map,@RequestBody List<Map<String, Object>> list);
}
