package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IZTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: ZTree树管理控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@RestController
@RequestMapping("/tree")
public class ZTreeController {

    @Autowired
    private IZTreeService zTreeService;

    /**
     * 获取地区树
     * @param map
     * @return
     */
    @PostMapping("/area")
    JSONArray getAreaTree(@RequestParam Map<String,Object> map){
        JSONObject json =  this.zTreeService.getAreaTree(map);
        return json.getJSONArray("data");
    }

    /**
     * 获取机构树
     * @param map
     * @return
     */
    @PostMapping("/organization")
    JSONArray getOrganizationTree(@RequestParam Map<String,Object> map){
        JSONObject json =  this.zTreeService.getOrganizationTree(map);
        return json.getJSONArray("data");
    }

    /**
     * 获取灾种树
     * @param map
     * @return
     */
    @PostMapping("/disaster")
    JSONArray getDisasterTree(@RequestParam Map<String,Object> map){
        JSONObject json =  this.zTreeService.getDisasterTree(map);
        return json.getJSONArray("data");
    }

    /**
     * 获取灾种级别树
     * @param map
     * @return
     */
    @PostMapping("/disaster/level")
    JSONArray getDisasterLevelTree(@RequestParam Map<String,Object> map){
        JSONObject json =  this.zTreeService.getDisasterLevelTree(map);
        return json.getJSONArray("data");
    }

    /**
     * 获取群组树
     * @param map
     * @return
     */
    @PostMapping("/user/group")
    JSONArray getUserGroupTree(@RequestParam Map<String,Object> map){
        JSONObject json =  this.zTreeService.getUserGroupTree(map);
        return json.getJSONArray("data");
    }

    /**
     * 获取机构群组树
     * @param map
     * @return
     */
    @PostMapping("/organization/group")
    JSONArray getOrganizationUserGroupTree(Map<String, Object> map){
        JSONObject json =  this.zTreeService.getOrganizationUserGroupTree(map);
        return json.getJSONArray("data");
    }
}
