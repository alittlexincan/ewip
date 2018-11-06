package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitSchool")
public class UnitSchoolController {

        @Autowired
        private IUnitSchoolService unitSchoolService;

        /**
         * 根据学校ID删除学校信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitSchoolService.deleteById(id);
        }

        /**
         * 批量删除学校信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitSchoolService.deleteBatch(id);
        }

        /**
         * 分页查询学校信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.unitSchoolService.selectAll(map);
        }

        /**
         * 查询学校列表
         * @return
         */
        @GetMapping("/selectList")
        public JSONObject selectList(){
                return this.unitSchoolService.selectList();
        };

}
