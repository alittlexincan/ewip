package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitAttractionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitAttractions")
public class UnitAttractionsController {

        @Autowired
        private IUnitAttractionsService unitAttractionsService;

        /**
         * 根据旅游景区ID删除旅游景区信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitAttractionsService.deleteById(id);
        }

        /**
         * 批量删除旅游景区信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitAttractionsService.deleteBatch(id);
        }

        /**
         * 分页查询旅游景区信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.unitAttractionsService.selectAll(map);
        }

        /**
         * 查询旅游景区列表
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                return this.unitAttractionsService.selectList(map);
        }

}
