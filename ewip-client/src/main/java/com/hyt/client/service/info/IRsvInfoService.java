package com.hyt.client.service.info;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Service("rsvInfoService")
@FeignClient("EWIP-SERVER")
public interface IRsvInfoService {

        /**
         * 根据ID删除信息
         * @param id
         * @return
         */
        @DeleteMapping("/rsvInfo/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除信息
         * @param id
         * @return
         */
        @PostMapping("/rsvInfo/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询信息
         * @param map
         * @return
         */
        @GetMapping("/rsvInfo/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询列表
         * @return
         */
        @GetMapping("/rsvInfo/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/rsvInfo/list")
        JSONObject selectList(@RequestParam Map<String, Object> map);

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/rsvInfo/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/rsvInfo/update")
        JSONObject update(@RequestParam Map<String, Object> map);


        /**
         * 数据导入
         * @return
         */
        @PostMapping("/rsvInfo/importData")
        JSONObject importData(@RequestParam Map<String, Object> map, @RequestBody List<Map<String, Object>> list);
}
