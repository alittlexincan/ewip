package com.hyt.server.service.impl.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.sys.Employee;
import com.hyt.server.mapper.sys.IEmployeeMapper;
import com.hyt.server.service.sys.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public PageInfo<Employee> selectAll(Map<String, Object> map) {
        System.out.println(map);
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Employee> userInfoList = this.employeeMapper.findAll(map);
        return new PageInfo<>(userInfoList);
    }


    @Override
    public Employee login(Map<String, Object> map) {
        return this.employeeMapper.login(map);
    }

    @Override
    public Employee selectById(Map<String, Object> map) {
        return this.employeeMapper.login(map);
    }

}
