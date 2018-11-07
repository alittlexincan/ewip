package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitHospital")
public class UnitHospitalController {

        @Autowired
        private IUnitHospitalService unitHospitalService;

        /**
         * 根据医院ID删除医院信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitHospitalService.deleteById(id);
        }

        /**
         * 批量删除医院信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitHospitalService.deleteBatch(id);
        }

        /**
         * 分页查询医院信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.unitHospitalService.selectAll(map);
        }

        /**
         * 查询医院列表
         * @return
         */
        @GetMapping("/selectList")
        public JSONObject selectList(){
                return this.unitHospitalService.selectList();
        };

}
