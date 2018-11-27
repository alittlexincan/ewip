package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IDisasterTypeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("disasterType")
public class DisasterTypeController {

        @Autowired
        private IDisasterTypeService disasterTypeService;


        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/insert")
        JSONObject insert(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("createUser", employee.getString("id"));
                return this.disasterTypeService.insert(map);
        }

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/update")
        JSONObject update(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("updateUser", employee.getString("id"));
                return this.disasterTypeService.update(map);
        }


        /**
         * 添加灾害种类信息
         * @param map
         * @return
         */
//        @PostMapping("/insert")
//        public JSONObject insert(@RequestParam Map<String,Object> map){
//            return this.disasterTypeService.insert(map);
//        }

        /**
         * 修改灾害种类信息
         * @param map
         * @return
         */
//        @PostMapping("/update")
//        public JSONObject update(@RequestParam Map<String,Object> map){
//            return this.disasterTypeService.update(map);
//        }

        /**
         * 根据灾害种类ID删除灾害种类信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.disasterTypeService.deleteById(id);
        }

        /**
         * 批量删除灾害种类信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.disasterTypeService.deleteBatch(id);
        }

        /**
         * 根据灾害种类ID查询灾害种类信息
         * @param id
         * @return
         */
        @PostMapping("/select/{id}")
        public JSONObject selectById(@PathVariable(value = "id") String id){
            return this.disasterTypeService.selectById(id);
        }


        /**
         * 分页查询灾害种类信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.disasterTypeService.selectAll(map);
        }

        /**
         * 查询列表
         * @param map
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                return this.disasterTypeService.selectList(map);
        }
}
