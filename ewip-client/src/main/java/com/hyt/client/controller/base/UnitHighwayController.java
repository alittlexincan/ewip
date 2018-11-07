package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitHighwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitHighway")
public class UnitHighwayController {

        @Autowired
        private IUnitHighwayService unitHighwayService;

        /**
         * 根据高速公路ID删除高速公路信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitHighwayService.deleteById(id);
        }

        /**
         * 批量删除高速公路信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitHighwayService.deleteBatch(id);
        }

        /**
         * 分页查询高速公路信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.unitHighwayService.selectAll(map);
        }

        /**
         * 查询高速公路列表
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                return this.unitHighwayService.selectList(map);
        }

}
