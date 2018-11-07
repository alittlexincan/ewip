package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitMarket")
public class UnitMarketController {

        @Autowired
        private IUnitMarketService unitMarketService;

        /**
         * 根据商场ID删除商场信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitMarketService.deleteById(id);
        }

        /**
         * 批量删除商场信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitMarketService.deleteBatch(id);
        }

        /**
         * 分页查询商场信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.unitMarketService.selectAll(map);
        }

        /**
         * 查询商场列表
         * @return
         */
        @GetMapping("/selectList")
        public JSONObject selectList(){
                return this.unitMarketService.selectList();
        };

}
