package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitMarketService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitMarket")
public class UnitMarketController {

        @Autowired
        private IUnitMarketService unitMarketService;

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
                return this.unitMarketService.insert(map);
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
                return this.unitMarketService.update(map);
        }

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
            return this.unitMarketService.selectAll(map);
        }

        /**
         * 查询商场列表
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
                return this.unitMarketService.selectList(map);
        }

}
