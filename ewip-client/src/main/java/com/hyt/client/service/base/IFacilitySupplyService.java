package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-应急物资接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("facilitySupplyService")
@FeignClient("EWIP-SERVER")
public interface IFacilitySupplyService {

        /**
         * 根据应急物资ID删除应急物资信息
         * @param id
         * @return
         */
        @DeleteMapping("/facilitySupply/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除应急物资信息
         * @param id
         * @return
         */
        @PostMapping("/facilitySupply/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询应急物资信息
         * @param map
         * @return
         */
        @GetMapping("/facilitySupply/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询应急物资信息列表
         * @param map
         * @return
         */
        @GetMapping("/facilitySupply/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
