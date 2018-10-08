package com.hyt.client.service.sys;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * zTree树接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("zTreeService")
@FeignClient("EWIP-SERVER")
public interface IZTreeService {


    /**
     * 查询菜单信息树
     * @param map
     * @return
     */
    @PostMapping("/tree/menu")
    JSONObject getMenuTree(@RequestParam Map<String, Object> map);

    /**
     * 查询地区信息树
     * @param map
     * @return
     */
    @PostMapping("/tree/area")
    JSONObject getAreaTree(@RequestParam Map<String, Object> map);

    /**
     * 查询机构信息树
     * @param map
     * @return
     */
    @PostMapping("/tree/organization")
    JSONObject getOrganizationTree(@RequestParam Map<String, Object> map);

    /**
     * 查询灾种信息树
     * @param map
     * @return
     */
    @PostMapping("/tree/disaster")
    JSONObject getDisasterTree(@RequestParam Map<String, Object> map);

    /**
     * 查询灾种级别信息树
     * @param map
     * @return
     */
    @PostMapping("/tree/disaster/level")
    JSONObject getDisasterLevelTree(@RequestParam Map<String, Object> map);

    /**
     * 查询群组信息树
     * @param map
     * @return
     */
    @PostMapping("/tree/user/group")
    JSONObject getUserGroupTree(@RequestParam Map<String, Object> map);

    /**
     * 查询群组对应受众个数信息树
     * @param map
     * @return
     */
    @PostMapping("/tree/user/group/count")
    JSONObject getUserGroupCountTree(@RequestParam Map<String, Object> map);

    /**
     * 查询机构对应群组信息树
     * @param map
     * @return
     */
    @PostMapping("/tree/organization/group")
    JSONObject getOrganizationUserGroupTree(Map<String, Object> map);

}
