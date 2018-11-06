package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IRiskForestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("riskForest")
public class RiskForestController {

        @Autowired
        private IRiskForestService riskForestService;

        /**
         * 根据森林火险点ID删除森林火险点信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.riskForestService.deleteById(id);
        }

        /**
         * 批量删除森林火险点信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.riskForestService.deleteBatch(id);
        }

        /**
         * 分页查询森林火险点信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.riskForestService.selectAll(map);
        }

}
