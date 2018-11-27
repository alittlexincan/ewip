package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-车站接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitStationService")
@FeignClient("EWIP-SERVER")
public interface IUnitStationService {

        /**
         * 根据车站ID删除车站信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitStation/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除车站信息
         * @param id
         * @return
         */
        @PostMapping("/unitStation/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询车站信息
         * @param map
         * @return
         */
        @GetMapping("/unitStation/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询车站列表
         * @return
         */
        @GetMapping("/unitStation/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/unitStation/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/unitStation/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/unitStation/update")
        JSONObject update(@RequestParam Map<String, Object> map);
}
