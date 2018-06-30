package com.hyt.client.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: XincanJiang
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("userService")
@FeignClient("EWIP-SERVER")
public interface IUserService {

    /**
     * 添加用户信息
     * @param map
     * @return
     */
    @PostMapping("/user/insert")
    JSONObject insert(@RequestParam Map<String,Object> map);

    /**
     * 修改用户信息
     * @param map
     * @return
     */
    @PostMapping("/user/update")
    JSONObject update(@RequestParam Map<String,Object> map);

    /**
     * 根据用户id删除用户信息
     * @param id
     * @return
     */
    @DeleteMapping("/user/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);

    /**
     * 根据ids批量删除用户信息
     * @param id
     * @return
     */
    @PostMapping("/user/delete/batch")
    JSONObject deleteByIds(@RequestParam("id") String id);

    /**
     * 根据用户id查询用户详细信息
     * @param id
     * @return
     */
    @GetMapping("/user/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id);

    /**
     * 分页查询用户信息
     * @param map
     * @return
     */
    @GetMapping("/user/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map);

}
