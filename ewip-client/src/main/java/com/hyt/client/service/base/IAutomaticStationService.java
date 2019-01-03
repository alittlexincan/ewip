package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-学校接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("automaticStationService")
@FeignClient("EWIP-SERVER")
public interface IAutomaticStationService {

        /**
         * 根据学校ID删除学校信息
         * @param id
         * @return
         */
        @DeleteMapping("/automaticStation/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除学校信息
         * @param id
         * @return
         */
        @PostMapping("/automaticStation/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询学校信息
         * @param map
         * @return
         */
        @GetMapping("/automaticStation/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询学校列表
         * @return
         */
        @GetMapping("/automaticStation/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/automaticStation/list")
        JSONObject selectList(@RequestParam Map<String, Object> map);

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/automaticStation/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/automaticStation/update")
        JSONObject update(@RequestParam Map<String, Object> map);
}
