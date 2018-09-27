package com.hyt.server.service.impl.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.sys.Employee;
import com.hyt.server.entity.sys.Permission;
import com.hyt.server.entity.sys.Role;
import com.hyt.server.mapper.sys.IEmployeeMapper;
import com.hyt.server.mapper.sys.IPermissionMapper;
import com.hyt.server.mapper.sys.IRoleMapper;
import com.hyt.server.service.sys.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: XincanJiang
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("employeeService")
public class EmployeeServiceImpl extends AbstractService<Employee> implements IEmployeeService {

    @Autowired
    private IEmployeeMapper employeeMapper;

    @Autowired
    private IRoleMapper roleMapper;

    @Autowired
    private IPermissionMapper permissionMapper;

    @Override
    public PageInfo<Employee> selectAll(Map<String, Object> map) {
        System.out.println(map);
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Employee> userInfoList = this.employeeMapper.findAll(map);
        return new PageInfo<>(userInfoList);
    }


    @Override
    public JSONObject login(Map<String, Object> map) {
        Employee employee = this.employeeMapper.login(map);
        JSONObject result = (JSONObject) JSON.toJSON(employee);
        if(employee !=null){
            Map<String, Object> param = new HashMap<>();
            param.put("employeeId", employee.getId());
            List<Role> roles = this.roleMapper.findRoleByEmployeeId(param);
            JSONArray roleArray = (JSONArray) JSON.toJSON(roles);
            result.put("roles", roleArray);
            if(roles.size()>0){
                StringBuilder sb = new StringBuilder();
                roles.forEach(role -> {
                    sb.append("," + role.getId());
                });
                param.remove("employeeId");
                param.put("roleId", sb.substring(1));
                List<Permission> permissions = this.permissionMapper.findPermissionByRoleId(param);
                List<Permission> permissionList = permissions.stream().collect(
                        Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getId()))), ArrayList::new)
                );
                JSONArray permissionArray = (JSONArray) JSON.toJSON(permissionList);
                result.put("permissions", permissionArray);
            }
        }
        return result;
    }

    @Override
    public Employee selectById(Map<String, Object> map) {
        return this.employeeMapper.login(map);
    }

}
