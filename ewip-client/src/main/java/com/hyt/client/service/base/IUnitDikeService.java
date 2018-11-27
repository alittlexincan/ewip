package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-提防接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitDikeService")
@FeignClient("EWIP-SERVER")
public interface IUnitDikeService {

        /**
         * 根据提防ID删除提防信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitDike/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除提防信息
         * @param id
         * @return
         */
        @PostMapping("/unitDike/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询提防信息
         * @param map
         * @return
         */
        @GetMapping("/unitDike/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询提防列表
         * @return
         */
        @GetMapping("/unitDike/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/unitDike/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/unitDike/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/unitDike/update")
        JSONObject update(@RequestParam Map<String, Object> map);
}
