package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IRiskMountainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("riskMountain")
public class RiskMountainController {

        @Autowired
        private IRiskMountainService riskMountainService;

        /**
         * 根据山洪沟ID删除山洪沟信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.riskMountainService.deleteById(id);
        }

        /**
         * 批量删除山洪沟信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.riskMountainService.deleteBatch(id);
        }

        /**
         * 分页查询山洪沟信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.riskMountainService.selectAll(map);
        }

        /**
         * 查询列表
         * @param map
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                return this.riskMountainService.selectList(map);
        }
}
