package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-水库接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitReservoirService")
@FeignClient("EWIP-SERVER")
public interface IUnitReservoirService {

        /**
         * 根据水库ID删除水库信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitReservoir/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除水库信息
         * @param id
         * @return
         */
        @PostMapping("/unitReservoir/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询水库信息
         * @param map
         * @return
         */
        @GetMapping("/unitReservoir/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询水库列表
         * @return
         */
        @GetMapping("/unitReservoir/selectList")
        JSONObject selectList();
}
