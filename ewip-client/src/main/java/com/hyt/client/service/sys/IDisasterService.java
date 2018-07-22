package com.hyt.client.service.sys;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 渠道手段信息接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("disasterService")
@FeignClient("EWIP-SERVER")
public interface IDisasterService {

    /**
     * 添加渠道手段信息
     * @param map
     * @return
     */
    @PostMapping("/disaster/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);

    /**
     * 修改渠道手段信息
     * @param map
     * @return
     */
    @PostMapping("/disaster/update")
    JSONObject update(@RequestParam Map<String, Object> map);

    /**
     * 根据地区id删除灾种信息
     * @param id
     * @return
     */
    @DeleteMapping("/disaster/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);

    /**
     * 根据ids批量删除灾种信息
     * @param id
     * @return
     */
    @PostMapping("/disaster/delete")
    JSONObject deleteBatch(@RequestParam("id") String id);

    /**
     * 根据用户id查询灾种详细信息
     * @param id
     * @return
     */
    @GetMapping("/disaster/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id);

    /**
     * 分页查询灾种信息
     * @param map
     * @return
     */
    @GetMapping("/disaster/select")
    JSONObject selectAll(@RequestParam Map<String, Object> map);

}
