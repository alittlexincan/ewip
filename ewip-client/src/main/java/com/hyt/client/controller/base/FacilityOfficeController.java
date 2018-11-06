package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IFacilityOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("facilityOffice")
public class FacilityOfficeController {

        @Autowired
        private IFacilityOfficeService facilityOfficeService;

        /**
         * 根据办公场所ID删除办公场所信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.facilityOfficeService.deleteById(id);
        }

        /**
         * 批量删除办公场所信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.facilityOfficeService.deleteBatch(id);
        }

        /**
         * 分页查询办公场所信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.facilityOfficeService.selectAll(map);
        }

}
