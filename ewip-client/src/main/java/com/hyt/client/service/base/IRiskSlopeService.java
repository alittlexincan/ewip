package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-陡坡接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("riskSlopeService")
@FeignClient("EWIP-SERVER")
public interface IRiskSlopeService {

        /**
         * 根据陡坡ID删除陡坡信息
         * @param id
         * @return
         */
        @DeleteMapping("/riskSlope/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除陡坡信息
         * @param id
         * @return
         */
        @PostMapping("/riskSlope/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询陡坡信息
         * @param map
         * @return
         */
        @GetMapping("/riskSlope/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/riskSlope/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
