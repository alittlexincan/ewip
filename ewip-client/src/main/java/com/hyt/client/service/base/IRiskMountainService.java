package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-山洪沟接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("riskMountainService")
@FeignClient("EWIP-SERVER")
public interface IRiskMountainService {

        /**
         * 根据山洪沟ID删除山洪沟信息
         * @param id
         * @return
         */
        @DeleteMapping("/riskMountain/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除山洪沟信息
         * @param id
         * @return
         */
        @PostMapping("/riskMountain/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询山洪沟信息
         * @param map
         * @return
         */
        @GetMapping("/riskMountain/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);
}
