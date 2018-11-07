package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-危险品场所接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitDangerService")
@FeignClient("EWIP-SERVER")
public interface IUnitDangerService {

        /**
         * 根据危险品场所ID删除危险品场所信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitDanger/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除危险品场所信息
         * @param id
         * @return
         */
        @PostMapping("/unitDanger/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询危险品场所信息
         * @param map
         * @return
         */
        @GetMapping("/unitDanger/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询危险品场所列表
         * @return
         */
        @GetMapping("/unitDanger/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/unitDanger/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
