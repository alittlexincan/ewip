package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-旅游景区接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitAttractionsService")
@FeignClient("EWIP-SERVER")
public interface IUnitAttractionsService {

        /**
         * 根据旅游景区ID删除旅游景区信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitAttractions/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除旅游景区信息
         * @param id
         * @return
         */
        @PostMapping("/unitAttractions/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询旅游景区信息
         * @param map
         * @return
         */
        @GetMapping("/unitAttractions/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询旅游景区列表
         * @return
         */
        @GetMapping("/unitAttractions/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/unitAttractions/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
