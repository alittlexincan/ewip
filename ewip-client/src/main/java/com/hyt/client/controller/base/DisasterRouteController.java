package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IDisasterRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("disasterRoute")
public class DisasterRouteController {

        @Autowired
        private IDisasterRouteService disasterRouteService;

        /**
         * 根据灾害路径ID删除灾害路径信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.disasterRouteService.deleteById(id);
        }

        /**
         * 批量删除灾害路径信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.disasterRouteService.deleteBatch(id);
        }

        /**
         * 分页查询灾害路径信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.disasterRouteService.selectAll(map);
        }

}
