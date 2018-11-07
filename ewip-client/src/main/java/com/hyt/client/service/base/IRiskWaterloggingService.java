package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-内涝隐患点接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("riskWaterloggingService")
@FeignClient("EWIP-SERVER")
public interface IRiskWaterloggingService {

        /**
         * 根据内涝隐患点ID删除内涝隐患点信息
         * @param id
         * @return
         */
        @DeleteMapping("/riskWaterlogging/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除内涝隐患点信息
         * @param id
         * @return
         */
        @PostMapping("/riskWaterlogging/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询内涝隐患点信息
         * @param map
         * @return
         */
        @GetMapping("/riskWaterlogging/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/riskWaterlogging/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
