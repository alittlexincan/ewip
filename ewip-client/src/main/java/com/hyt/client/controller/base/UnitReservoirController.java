package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitReservoirService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitReservoir")
public class UnitReservoirController {

        @Autowired
        private IUnitReservoirService unitReservoirService;

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
                return this.unitReservoirService.insert(map);
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
                return this.unitReservoirService.update(map);
        }


        /**
         * 根据水库ID删除水库信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitReservoirService.deleteById(id);
        }

        /**
         * 批量删除水库信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitReservoirService.deleteBatch(id);
        }

        /**
         * 分页查询水库信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.unitReservoirService.selectAll(map);
        }

        /**
         * 查询水库列表
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                return this.unitReservoirService.selectList(map);
        }

}
