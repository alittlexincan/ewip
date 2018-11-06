package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-易涝区接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("riskWaterloggingAreaService")
@FeignClient("EWIP-SERVER")
public interface IRiskWaterloggingAreaService {

        /**
         * 根据易涝区ID删除易涝区信息
         * @param id
         * @return
         */
        @DeleteMapping("/riskWaterloggingArea/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除易涝区信息
         * @param id
         * @return
         */
        @PostMapping("/riskWaterloggingArea/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询易涝区信息
         * @param map
         * @return
         */
        @GetMapping("/riskWaterloggingArea/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);
}
