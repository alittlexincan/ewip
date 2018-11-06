package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IRiskSlopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("riskSlope")
public class RiskSlopeController {

        @Autowired
        private IRiskSlopeService riskSlopeService;

        /**
         * 根据陡坡ID删除陡坡信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.riskSlopeService.deleteById(id);
        }

        /**
         * 批量删除陡坡信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.riskSlopeService.deleteBatch(id);
        }

        /**
         * 分页查询陡坡信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.riskSlopeService.selectAll(map);
        }

}
