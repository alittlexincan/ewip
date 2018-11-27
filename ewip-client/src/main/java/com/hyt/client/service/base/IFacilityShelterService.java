package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-应急避难所接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("facilityShelterService")
@FeignClient("EWIP-SERVER")
public interface IFacilityShelterService {

        /**
         * 根据应急避难所ID删除应急避难所信息
         * @param id
         * @return
         */
        @DeleteMapping("/facilityShelter/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除应急避难所信息
         * @param id
         * @return
         */
        @PostMapping("/facilityShelter/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询应急避难所信息
         * @param map
         * @return
         */
        @GetMapping("/facilityShelter/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询应急避难所信息列表
         * @param map
         * @return
         */
        @GetMapping("/facilityShelter/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/facilityShelter/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/facilityShelter/update")
        JSONObject update(@RequestParam Map<String, Object> map);
}
