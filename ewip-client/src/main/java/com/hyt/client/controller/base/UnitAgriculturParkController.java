package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitAgriculturParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitAgriculturPark")
public class UnitAgriculturParkController {

        @Autowired
        private IUnitAgriculturParkService unitAgriculturParkService;

        /**
         * 根据农业园区ID删除农业园区信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitAgriculturParkService.deleteById(id);
        }

        /**
         * 批量删除农业园区信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitAgriculturParkService.deleteBatch(id);
        }

        /**
         * 分页查询农业园区信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.unitAgriculturParkService.selectAll(map);
        }

        /**
         * 查询农业园区列表
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                return this.unitAgriculturParkService.selectList(map);
        }
}
