package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IRiskFloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("riskFlood")
public class RiskFloodController {

        @Autowired
        private IRiskFloodService riskFloodService;

        /**
         * 根据中小河流ID删除中小河流信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.riskFloodService.deleteById(id);
        }

        /**
         * 批量删除中小河流信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.riskFloodService.deleteBatch(id);
        }

        /**
         * 分页查询中小河流信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.riskFloodService.selectAll(map);
        }

        /**
         * 查询列表
         * @param map
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                return this.riskFloodService.selectList(map);
        }

}
