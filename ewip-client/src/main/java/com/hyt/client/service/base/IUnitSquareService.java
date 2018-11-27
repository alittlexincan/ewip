package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-广场接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitSquareService")
@FeignClient("EWIP-SERVER")
public interface IUnitSquareService {

        /**
         * 根据广场ID删除广场信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitSquare/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除广场信息
         * @param id
         * @return
         */
        @PostMapping("/unitSquare/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询广场信息
         * @param map
         * @return
         */
        @GetMapping("/unitSquare/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询广场列表
         * @return
         */
        @GetMapping("/unitSquare/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/unitSquare/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);


        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/unitSquare/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/unitSquare/update")
        JSONObject update(@RequestParam Map<String, Object> map);
}
