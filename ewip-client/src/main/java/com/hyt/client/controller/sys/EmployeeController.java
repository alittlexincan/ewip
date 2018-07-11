package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 员工信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 员工登录信息
     * @param map
     * @return
     */
    @PostMapping("/login")
    JSONObject login(@RequestParam Map<String,Object> map){
        return this.employeeService.login(map);
    }

    /**
     * 添加员工信息
     * @param map
     * @return
     */
    @PostMapping("/insert")
    JSONObject insert(@RequestParam Map<String,Object> map){
        return this.employeeService.insert(map);
    }

    /**
     * 修改员工信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    JSONObject update(@RequestParam Map<String,Object> map){
        return this.employeeService.update(map);
    }

    /**
     * 根据员工id删除用户信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id){
        return this.employeeService.deleteById(id);
    }

    /**
     * 根据ids批量删除员工信息
     * @param id
     * @return
     */
    @PostMapping("/delete")
    JSONObject deleteBatch(@RequestParam(value = "id") String id){
        return this.employeeService.deleteBatch(id);
    }

    /**
     * 根据用户id查询员工详细信息
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id){
        return this.employeeService.selectById(id);
    }

    /**
     * 分页查询用户信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map){
        return this.employeeService.selectAll(map);
    }


}
