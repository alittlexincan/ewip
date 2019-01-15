package com.hyt.client.controller.config;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.config.ICimissConfigService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: cimiss信息控制层
 * @Date: Created in 10:07 2019-1-9
 * @Modified By:
 */
@RestController
@RequestMapping("cimissConfig")
public class CimissConfigController {

    @Autowired
    private ICimissConfigService cimissConfigService;


    /**
     * 添加Cimiss信息
     *
     * @param map
     * @return
     */
    @PostMapping("/insert")
    JSONObject insert(@RequestParam Map<String,Object> map){
        return this.cimissConfigService.insert(map);
    }

    /**
     * 修改Cimiss信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    JSONObject update(@RequestParam Map<String,Object> map){
        return this.cimissConfigService.update(map);
    }

    /**
     * 根据id删除Cimiss信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id){
        return this.cimissConfigService.deleteById(id);
    }

    /**
     * 根据ids批量删除Cimiss信息
     * @param id
     * @return
     */
    @PostMapping("/delete")
    JSONObject deleteBatch(@RequestParam(value = "id") String id){
        return this.cimissConfigService.deleteBatch(id);
    }

    /**
     * 根据id查询Cimiss信息
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id){
        return this.cimissConfigService.selectById(id);
    }

    /**
     * 分页查询Cimiss信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("empAreaId", employee.getString("areaId"));
        return this.cimissConfigService.selectAll(map);
    }

}
