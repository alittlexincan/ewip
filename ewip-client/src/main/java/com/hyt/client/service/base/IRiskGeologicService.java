package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-地质灾害隐患点接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("riskGeologicService")
@FeignClient("EWIP-SERVER")
public interface IRiskGeologicService {

        /**
         * 根据地质灾害隐患点ID删除地质灾害隐患点信息
         * @param id
         * @return
         */
        @DeleteMapping("/riskGeologic/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除地质灾害隐患点信息
         * @param id
         * @return
         */
        @PostMapping("/riskGeologic/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询地质灾害隐患点信息
         * @param map
         * @return
         */
        @GetMapping("/riskGeologic/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询列表
         * @param map
         * @return
         */
        @GetMapping("/riskGeologic/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
