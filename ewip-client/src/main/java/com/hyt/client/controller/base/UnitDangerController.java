package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitDangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitDanger")
public class UnitDangerController {

        @Autowired
        private IUnitDangerService unitDangerService;

        /**
         * 根据危险品场所ID删除危险品场所信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitDangerService.deleteById(id);
        }

        /**
         * 批量删除危险品场所信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitDangerService.deleteBatch(id);
        }

        /**
         * 分页查询危险品场所信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.unitDangerService.selectAll(map);
        }

        /**
         * 查询危险品场所列表
         * @return
         */
        @GetMapping("/selectList")
        public JSONObject selectList(){
                return this.unitDangerService.selectList();
        };

}
