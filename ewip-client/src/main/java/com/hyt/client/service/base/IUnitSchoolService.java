package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-学校接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("unitSchoolService")
@FeignClient("EWIP-SERVER")
public interface IUnitSchoolService {

        /**
         * 根据学校ID删除学校信息
         * @param id
         * @return
         */
        @DeleteMapping("/unitSchool/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除学校信息
         * @param id
         * @return
         */
        @PostMapping("/unitSchool/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询学校信息
         * @param map
         * @return
         */
        @GetMapping("/unitSchool/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);

        /**
         * 查询学校列表
         * @return
         */
        @GetMapping("/unitSchool/selectList")
        JSONObject selectList();

        /**
         * 查询列表，用于地图展示
         * @param map
         * @return
         */
        @GetMapping("/unitSchool/list")
        JSONObject selectList(@RequestParam Map<String,Object> map);

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/unitSchool/insert")
        JSONObject insert(@RequestParam Map<String, Object> map);

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/unitSchool/update")
        JSONObject update(@RequestParam Map<String, Object> map);
}
