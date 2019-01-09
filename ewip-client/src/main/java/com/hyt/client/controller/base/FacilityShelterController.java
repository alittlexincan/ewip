package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IFacilityShelterService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("facilityShelter")
public class FacilityShelterController {

        @Autowired
        private IFacilityShelterService facilityShelterService;

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
                return this.facilityShelterService.insert(map);
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
                return this.facilityShelterService.update(map);
        }

        /**
         * 根据应急避难所ID删除应急避难所信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.facilityShelterService.deleteById(id);
        }

        /**
         * 批量删除应急避难所信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.facilityShelterService.deleteBatch(id);
        }

        /**
         * 分页查询应急避难所信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("empAreaId", employee.getString("areaId"));
            return this.facilityShelterService.selectAll(map);
        }

        /**
         * 查询列表
         * @param map
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("empAreaId", employee.getString("areaId"));
                return this.facilityShelterService.selectList(map);
        }
}
