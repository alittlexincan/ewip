package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IFacilitySupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("facilitySupply")
public class FacilitySupplyController {

        @Autowired
        private IFacilitySupplyService facilitySupplyService;

        /**
         * 根据应急物资ID删除应急物资信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.facilitySupplyService.deleteById(id);
        }

        /**
         * 批量删除应急物资信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.facilitySupplyService.deleteBatch(id);
        }

        /**
         * 分页查询应急物资信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.facilitySupplyService.selectAll(map);
        }

        /**
         * 查询列表
         * @param map
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                return this.facilitySupplyService.selectList(map);
        }
}
