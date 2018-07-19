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
@Service("channelService")
@FeignClient("EWIP-SERVER")
public interface IChannelService {

    /**
     * 添加渠道手段信息
     * @param map
     * @return
     */
    @PostMapping("/channel/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);

    /**
     * 修改渠道手段信息
     * @param map
     * @return
     */
    @PostMapping("/channel/update")
    JSONObject update(@RequestParam Map<String, Object> map);

    /**
     * 根据地区id删除渠道手段信息
     * @param id
     * @return
     */
    @DeleteMapping("/channel/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);

    /**
     * 根据ids批量删除渠道手段信息
     * @param id
     * @return
     */
    @PostMapping("/channel/delete")
    JSONObject deleteBatch(@RequestParam("id") String id);

    /**
     * 根据用户id查询渠道手段详细信息
     * @param id
     * @return
     */
    @GetMapping("/channel/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id);

    /**
     * 分页查询渠道手段信息
     * @param map
     * @return
     */
    @GetMapping("/channel/select")
    JSONObject selectAll(@RequestParam Map<String, Object> map);

    /**
     * 查询渠道手段信息
     * @param map
     * @return
     */
    @PostMapping("/channel/list")
    JSONObject selectList(@RequestParam Map<String, Object> map);

}
