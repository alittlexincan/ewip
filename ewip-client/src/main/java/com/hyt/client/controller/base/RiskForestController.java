package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IRiskForestService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("riskForest")
public class RiskForestController {

        @Autowired
        private IRiskForestService riskForestService;

        /**
         * 根据森林火险点ID删除森林火险点信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.riskForestService.deleteById(id);
        }

        /**
         * 批量删除森林火险点信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.riskForestService.deleteBatch(id);
        }

        /**
         * 分页查询森林火险点信息
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
            return this.riskForestService.selectAll(map);
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
                String areaCode=employee.getString("areaCode");
                if(employee.getString("level").equals("1")){
                        areaCode=areaCode.substring(0,2);
                }else if(employee.getString("level").equals("2")){
                        areaCode=areaCode.substring(0,4);
                }else if(employee.getString("level").equals("3")){
                        areaCode=areaCode.substring(0,6);
                }
                map.put("areaCode", areaCode);
                return this.riskForestService.selectList(map);
        }
}
