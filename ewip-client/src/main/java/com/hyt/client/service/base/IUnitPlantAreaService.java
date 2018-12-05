package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-农作物种植区接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitPlantAreaService")
@FeignClient("EWIP-SERVER")
public interface IUnitPlantAreaService {

        /**
         * 根据农作物种植区ID删除农作物种植区信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitPlantArea/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除农作物种植区信息
         * @param id
         * @return
         */
        @PostMapping("/unitPlantArea/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询农作物种植区信息
         * @param map
         * @return
         */
        @GetMapping("/unitPlantArea/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询农作物种植区列表
         * @return
         */
        @GetMapping("/unitPlantArea/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/unitPlantArea/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/unitPlantArea/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/unitPlantArea/update")
        JSONObject update(@RequestParam Map<String, Object> map);
}
