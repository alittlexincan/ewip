package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitStationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitStation")
public class UnitStationController {

        @Autowired
        private IUnitStationService unitStationService;

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
                return this.unitStationService.insert(map);
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
                return this.unitStationService.update(map);
        }

        /**
         * 根据车站ID删除车站信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitStationService.deleteById(id);
        }

        /**
         * 批量删除车站信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitStationService.deleteBatch(id);
        }

        /**
         * 分页查询车站信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("empAreaId", employee.getString("areaId"));
                String areaCode=employee.getString("areaCode");
                if(employee.getString("level").equals("1")){
                        areaCode=areaCode.substring(0,2);
                }else if(employee.getString("level").equals("2")){
                        areaCode=areaCode.substring(0,4);
                }else if(employee.getString("level").equals("3")){
                        areaCode=areaCode.substring(0,6);
                }
                map.put("areaCode", areaCode);
            return this.unitStationService.selectAll(map);
        }

        /**
         * 查询车站列表
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("empAreaId", employee.getString("areaId"));
                String areaCode=employee.getString("areaCode");
                if(employee.getString("level").equals("1")){
                        areaCode=areaCode.substring(0,2);
                }else if(employee.getString("level").equals("2")){
                        areaCode=areaCode.substring(0,4);
                }else if(employee.getString("level").equals("3")){
                        areaCode=areaCode.substring(0,6);
                }
                map.put("areaCode", areaCode);
                return this.unitStationService.selectList(map);
        }

}
