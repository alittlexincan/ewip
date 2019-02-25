package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description: 实时灾情接口层
 * @Date: Created in 15:30 2018-11-1
 * @Modified By:
 */
@Service("realTimeDisasterService")
@FeignClient("EWIP-SERVER")
public interface IRealTimeDisasterService {


        /**
         * 实时灾情灾情信息
         * @param map
         * @return
         */
        @PostMapping("/realTimeDisaster/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改实时灾情信息
         * @param map
         * @return
         */
        @PostMapping("/realTimeDisaster/update")
        JSONObject update(@RequestParam Map<String, Object> map);

        /**
         * 根据实时灾情ID删除实时灾情信息
         * @param id
         * @return
         */
        @DeleteMapping("/realTimeDisaster/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除实时灾情信息
         * @param id
         * @return
         */
        @PostMapping("/realTimeDisaster/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);

        /**
         * 根据实时灾情ID查询实时灾情信息
         * @param id
         * @return
         */
        @PostMapping("/realTimeDisaster/select/{id}")
        JSONObject selectById(@PathVariable(value = "id") String id);


        /**
         * 分页查询实时灾情信息
         * @param map
         * @return
         */
        @GetMapping("/realTimeDisaster/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 多条件查询实时灾情信息
         * @param map
         * @return
         */
        @GetMapping("/realTimeDisaster/config")
        JSONObject selectConfig(@RequestParam Map<String, Object> map);


        /**
         * 查询实时灾情信息列表
         * @param map
         * @return
         */
        @GetMapping("/realTimeDisaster/list")
        JSONObject selectList(@RequestParam Map<String, Object> map);

        @PostMapping("/realTimeDisaster/selectFile")
        JSONObject selectFile(@RequestParam Map<String,Object> map);
}
