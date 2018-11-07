package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitPlantAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitPlantArea")
public class UnitPlantAreaController {

        @Autowired
        private IUnitPlantAreaService unitPlantAreaService;

        /**
         * 根据农作物种植区ID删除农作物种植区信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitPlantAreaService.deleteById(id);
        }

        /**
         * 批量删除农作物种植区信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitPlantAreaService.deleteBatch(id);
        }

        /**
         * 分页查询农作物种植区信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.unitPlantAreaService.selectAll(map);
        }

        /**
         * 查询农作物种植区列表
         * @return
         */
        @GetMapping("/selectList")
        public JSONObject selectList(){
                return this.unitPlantAreaService.selectList();
        };

}
