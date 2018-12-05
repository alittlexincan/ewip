package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-农业园区接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitAgriculturParkService")
@FeignClient("EWIP-SERVER")
public interface IUnitAgriculturParkService {

        /**
         * 根据农业园区ID删除农业园区信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitAgriculturPark/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除农业园区信息
         * @param id
         * @return
         */
        @PostMapping("/unitAgriculturPark/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询农业园区信息
         * @param map
         * @return
         */
        @GetMapping("/unitAgriculturPark/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询农业园区列表
         * @return
         */
        @GetMapping("/unitAgriculturPark/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/unitAgriculturPark/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/unitAgriculturPark/update")
        JSONObject update(@RequestParam Map<String, Object> map);
}
