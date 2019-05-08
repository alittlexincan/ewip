package com.hyt.client.service.info;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Service("feInfoService")
@FeignClient("EWIP-SERVER")
public interface IFeInfoService {

        /**
         * 根据ID删除信息
         * @param id
         * @return
         */
        @DeleteMapping("/feInfo/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除信息
         * @param id
         * @return
         */
        @PostMapping("/feInfo/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询信息
         * @param map
         * @return
         */
        @GetMapping("/feInfo/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询列表
         * @return
         */
        @GetMapping("/feInfo/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/feInfo/list")
        JSONObject selectList(@RequestParam Map<String, Object> map);

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/feInfo/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/feInfo/update")
        JSONObject update(@RequestParam Map<String, Object> map);


        /**
         * 数据导入
         * @return
         */
        @PostMapping("/feInfo/importData")
        JSONObject importData(@RequestParam Map<String, Object> map, @RequestBody List<Map<String, Object>> list);
}
