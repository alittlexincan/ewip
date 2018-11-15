package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 受众信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 添加受众信息
     * @param map
     * @return
     */
    @PostMapping("/insert")
    public JSONObject insert(@RequestParam Map<String,Object> map){
        return this.userService.insert(map);
    }

    /**
     * 修改受众信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    public JSONObject update(@RequestParam Map<String,Object> map){
        return this.userService.update(map);
    }

    /**
     * 根据受众ID删除受众信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public JSONObject deleteById(@PathVariable(value = "id") String id){
        return this.userService.deleteById(id);
    }

    /**
     * 批量删除受众信息
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public JSONObject deleteBatch(@RequestParam(value = "id") String id){
        return this.userService.deleteBatch(id);
    }

    /**
     * 根据受众ID查询受众信息
     * @param id
     * @return
     */
    @PostMapping("/select/{id}")
    public JSONObject selectById(@PathVariable(value = "id") String id){
        return this.userService.selectById(id);
    }


    /**
     * 分页查询受众信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    public JSONObject selectAll(@RequestParam Map<String,Object> map){
        return this.userService.selectAll(map);
    }

    /**
     * 查询受众列表信息
     * @param map
     * @return
     */
    @GetMapping("/list")
    public JSONObject selectList(@RequestParam Map<String,Object> map){
        return this.userService.selectList(map);
    }


    /**
     * 查询受众的详细信息
     * @param map
     * @return
     */
    @PostMapping("/userDetails")
    public JSONObject userDetails(@RequestParam Map<String,Object> map){

        return this.userService.userDetails(map);
    }

}
