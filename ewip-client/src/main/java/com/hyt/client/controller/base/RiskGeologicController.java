package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IRiskGeologicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("riskGeologic")
public class RiskGeologicController {

        @Autowired
        private IRiskGeologicService riskGeologicService;

        /**
         * 根据地质灾害隐患点ID删除地质灾害隐患点信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.riskGeologicService.deleteById(id);
        }

        /**
         * 批量删除地质灾害隐患点信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.riskGeologicService.deleteBatch(id);
        }

        /**
         * 分页查询地质灾害隐患点信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.riskGeologicService.selectAll(map);
        }

}
