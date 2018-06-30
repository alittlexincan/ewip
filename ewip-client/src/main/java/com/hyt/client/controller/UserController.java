package com.hyt.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: XincanJiang
 * @Description: 用户信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 添加用户信息
     * @param map
     * @return
     */
    @PostMapping("/insert")
    JSONObject insert(@RequestParam Map<String,Object> map){
        return this.userService.insert(map);
    }

    /**
     * 修改用户信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    JSONObject update(@RequestParam Map<String,Object> map){
        return this.userService.update(map);
    }

    /**
     * 根据用户id删除用户信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id){
        return this.userService.deleteById(id);
    }

    /**
     * 根据ids批量删除用户信息
     * @param id
     * @return
     */
    @PostMapping("/delete/batch")
    JSONObject deleteByIds(@RequestParam String id){
        return this.userService.deleteByIds(id);
    }

    /**
     * 根据用户id查询用户详细信息
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id){
        return this.userService.selectById(id);
    }

    /**
     * 分页查询用户信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map){
        return this.userService.selectAll(map);
    }


}
