package com.hyt.client.service.sys;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 机构信息接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("organizationService")
@FeignClient("EWIP-SERVER")
public interface IOrganizationService {

    /**
     * 添加机构信息
     * @param map
     * @return
     */
    @PostMapping("/area/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);

    /**
     * 修改机构信息
     * @param map
     * @return
     */
    @PostMapping("/area/update")
    JSONObject update(@RequestParam Map<String, Object> map);

    /**
     * 根据机构id删除机构信息
     * @param id
     * @return
     */
    @DeleteMapping("/area/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);

    /**
     * 根据ids批量删除机构信息
     * @param id
     * @return
     */
    @PostMapping("/area/delete")
    JSONObject deleteBatch(@RequestParam("id") String id);

    /**
     * 根据用户id查询机构详细信息
     * @param id
     * @return
     */
    @GetMapping("/area/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id);

    /**
     * 分页查询机构信息
     * @param map
     * @return
     */
    @GetMapping("/area/select")
    JSONObject selectAll(@RequestParam Map<String, Object> map);

}
