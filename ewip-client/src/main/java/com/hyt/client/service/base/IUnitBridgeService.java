package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-桥梁接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitBridgeService")
@FeignClient("EWIP-SERVER")
public interface IUnitBridgeService {

        /**
         * 根据桥梁ID删除桥梁信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitBridge/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除桥梁信息
         * @param id
         * @return
         */
        @PostMapping("/unitBridge/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询桥梁信息
         * @param map
         * @return
         */
        @GetMapping("/unitBridge/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询桥梁列表
         * @return
         */
        @GetMapping("/unitBridge/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/unitBridge/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
