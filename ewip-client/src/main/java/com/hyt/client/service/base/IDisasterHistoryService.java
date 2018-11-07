package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-历史灾情接口层
 * @Date: Created in 15:30 2018-11-1
 * @Modified By:
 */
@Service("disasterHistoryService")
@FeignClient("EWIP-SERVER")
public interface IDisasterHistoryService {


        /**
         * 添加历史灾情信息
         * @param map
         * @return
         */
        @PostMapping("/disasterHistory/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改历史灾情信息
         * @param map
         * @return
         */
        @PostMapping("/disasterHistory/update")
        JSONObject update(@RequestParam Map<String, Object> map);

        /**
         * 根据历史灾情ID删除历史灾情信息
         * @param id
         * @return
         */
        @DeleteMapping("/disasterHistory/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除历史灾情信息
         * @param id
         * @return
         */
        @PostMapping("/disasterHistory/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);

        /**
         * 根据历史灾情ID查询历史灾情信息
         * @param id
         * @return
         */
        @PostMapping("/disasterHistory/select/{id}")
        JSONObject selectById(@PathVariable(value = "id") String id);


        /**
         * 分页查询历史灾情信息
         * @param map
         * @return
         */
        @GetMapping("/disasterHistory/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 多条件查询历史灾情信息
         * @param map
         * @return
         */
        @GetMapping("/disasterHistory/config")
        JSONObject selectConfig(@RequestParam Map<String, Object> map);


        /**
         * 查询灾害历史信息列表
         * @param map
         * @return
         */
        @GetMapping("/disasterHistory/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
