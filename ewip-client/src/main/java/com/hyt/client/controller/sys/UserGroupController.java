package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 群组信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("group")
public class UserGroupController {

    @Autowired
    private IUserGroupService userGroupService;

    /**
     * 添加群组信息
     * @param map
     * @return
     */
    @PostMapping("/insert")
    public JSONObject insert(@RequestParam Map<String, Object> map){
        return this.userGroupService.insert(map);
    }


    /**
     * 修改群组信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    public JSONObject update(@RequestParam Map<String,Object> map){
        return this.userGroupService.update(map);
    }

    /**
     * 根据群组ID删除群组信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public JSONObject deleteById(@PathVariable(value = "id") String id){
        return this.userGroupService.deleteById(id);
    }

    /**
     * 批量删除群组信息
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public JSONObject deleteBatch(@RequestParam(value = "id") String id){
        return this.userGroupService.deleteBatch(id);
    }

    /**
     * 根据群组ID查询受众信息
     * @param id
     * @return
     */
    @PostMapping("/select/{id}")
    public JSONObject selectById(@PathVariable(value = "id") String id){
        return this.userGroupService.selectById(id);
    }

    /**
     * 分页查询群组信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    public JSONObject selectAll(@RequestParam Map<String,Object> map){
        return this.userGroupService.selectAll(map);
    }


    /**
     * 查询群组信息
     * @param map
     * @return
     */
    @PostMapping("/selectGroup")
    public JSONObject selectGroup(@RequestParam Map<String,Object> map){
        return this.userGroupService.selectGroup(map);
    }

}
