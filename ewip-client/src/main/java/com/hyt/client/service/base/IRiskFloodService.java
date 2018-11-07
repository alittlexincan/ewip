package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-中小河流接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("riskFloodService")
@FeignClient("EWIP-SERVER")
public interface IRiskFloodService {

        /**
         * 根据中小河流ID删除中小河流信息
         * @param id
         * @return
         */
        @DeleteMapping("/riskFlood/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除中小河流信息
         * @param id
         * @return
         */
        @PostMapping("/riskFlood/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询中小河流信息
         * @param map
         * @return
         */
        @GetMapping("/riskFlood/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/riskFlood/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
