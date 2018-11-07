package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-森林火险点接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("riskForestService")
@FeignClient("EWIP-SERVER")
public interface IRiskForestService {

        /**
         * 根据森林火险点ID删除森林火险点信息
         * @param id
         * @return
         */
        @DeleteMapping("/riskForest/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除森林火险点信息
         * @param id
         * @return
         */
        @PostMapping("/riskForest/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询森林火险点信息
         * @param map
         * @return
         */
        @GetMapping("/riskForest/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询列表
         * @param map
         * @return
         */
        @GetMapping("/riskForest/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
