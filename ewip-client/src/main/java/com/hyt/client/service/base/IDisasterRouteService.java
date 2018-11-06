package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-灾害路径接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("disasterRouteService")
@FeignClient("EWIP-SERVER")
public interface IDisasterRouteService {

        /**
         * 根据灾害路径ID删除灾害路径信息
         * @param id
         * @return
         */
        @DeleteMapping("/disasterRoute/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除灾害路径信息
         * @param id
         * @return
         */
        @PostMapping("/disasterRoute/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询灾害路径信息
         * @param map
         * @return
         */
        @GetMapping("/disasterRoute/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);
}
