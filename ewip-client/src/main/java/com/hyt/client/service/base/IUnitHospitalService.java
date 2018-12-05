package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-医院接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitHospitalService")
@FeignClient("EWIP-SERVER")
public interface IUnitHospitalService {

        /**
         * 根据医院ID删除医院信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitHospital/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除医院信息
         * @param id
         * @return
         */
        @PostMapping("/unitHospital/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询医院信息
         * @param map
         * @return
         */
        @GetMapping("/unitHospital/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询医院列表
         * @return
         */
        @GetMapping("/unitHospital/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/unitHospital/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/unitHospital/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/unitHospital/update")
        JSONObject update(@RequestParam Map<String, Object> map);
}
