package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-洼地接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("riskDepressionService")
@FeignClient("EWIP-SERVER")
public interface IRiskDepressionService {

        /**
         * 根据洼地ID删除洼地信息
         * @param id
         * @return
         */
        @DeleteMapping("/riskDepression/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除洼地信息
         * @param id
         * @return
         */
        @PostMapping("/riskDepression/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询洼地信息
         * @param map
         * @return
         */
        @GetMapping("/riskDepression/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);
}
