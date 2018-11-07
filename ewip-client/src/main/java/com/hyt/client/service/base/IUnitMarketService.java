package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-商场接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitMarketService")
@FeignClient("EWIP-SERVER")
public interface IUnitMarketService {

        /**
         * 根据商场ID删除商场信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitMarket/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除商场信息
         * @param id
         * @return
         */
        @PostMapping("/unitMarket/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询商场信息
         * @param map
         * @return
         */
        @GetMapping("/unitMarket/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询商场列表
         * @return
         */
        @GetMapping("/unitMarket/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/unitMarket/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
