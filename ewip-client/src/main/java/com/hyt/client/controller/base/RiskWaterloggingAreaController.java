package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IRiskWaterloggingAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("riskWaterloggingArea")
public class RiskWaterloggingAreaController {

        @Autowired
        private IRiskWaterloggingAreaService riskWaterloggingAreaService;

        /**
         * 根据易涝区ID删除易涝区信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.riskWaterloggingAreaService.deleteById(id);
        }

        /**
         * 批量删除易涝区信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.riskWaterloggingAreaService.deleteBatch(id);
        }

        /**
         * 分页查询易涝区信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.riskWaterloggingAreaService.selectAll(map);
        }

        /**
         * 查询列表
         * @param map
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                return this.riskWaterloggingAreaService.selectList(map);
        }
}
