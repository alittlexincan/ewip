package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitSquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitSquare")
public class UnitSquareController {

        @Autowired
        private IUnitSquareService unitSquareService;

        /**
         * 根据广场ID删除广场信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitSquareService.deleteById(id);
        }

        /**
         * 批量删除广场信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitSquareService.deleteBatch(id);
        }

        /**
         * 分页查询广场信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.unitSquareService.selectAll(map);
        }

        /**
         * 查询广场列表
         * @return
         */
        @GetMapping("/selectList")
        public JSONObject selectList(){
                return this.unitSquareService.selectList();
        };

}
