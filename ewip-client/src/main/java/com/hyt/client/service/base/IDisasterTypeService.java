package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-灾害种类接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("disasterTypeService")
@FeignClient("EWIP-SERVER")
public interface IDisasterTypeService {


        /**
         * 添加灾害种类信息
         * @param map
         * @return
         */
        @PostMapping("/disasterType/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改灾害种类信息
         * @param map
         * @return
         */
        @PostMapping("/disasterType/update")
        JSONObject update(@RequestParam Map<String, Object> map);

        /**
         * 根据灾害种类ID删除灾害种类信息
         * @param id
         * @return
         */
        @DeleteMapping("/disasterType/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除灾害种类信息
         * @param id
         * @return
         */
        @PostMapping("/disasterType/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);

        /**
         * 根据灾害种类ID查询灾害种类信息
         * @param id
         * @return
         */
        @PostMapping("/disasterType/select/{id}")
        JSONObject selectById(@PathVariable(value = "id") String id);


        /**
         * 分页查询灾害种类信息
         * @param map
         * @return
         */
        @GetMapping("/disasterType/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 多条件查询灾害种类信息
         * @param map
         * @return
         */
        @GetMapping("/disasterType/config")
        JSONObject selectConfig(@RequestParam Map<String, Object> map);

        /**
         * 查询灾害种类信息列表
         * @param map
         * @return
         */
        @GetMapping("/disasterType/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);
}
