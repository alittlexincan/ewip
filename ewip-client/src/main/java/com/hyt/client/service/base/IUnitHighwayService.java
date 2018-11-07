package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-高速公路接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitHighwayService")
@FeignClient("EWIP-SERVER")
public interface IUnitHighwayService {

        /**
         * 根据高速公路ID删除高速公路信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitHighway/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除高速公路信息
         * @param id
         * @return
         */
        @PostMapping("/unitHighway/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询高速公路信息
         * @param map
         * @return
         */
        @GetMapping("/unitHighway/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询高速公路列表
         * @return
         */
        @GetMapping("/unitHighway/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/unitHighway/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
