package com.hyt.server.mapper.sys;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.sys.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: XincanJiang
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("employeeMapper")
public interface IEmployeeMapper extends IBaseMapper<Employee> {

    List<Employee> findAll(Map<String,Object> map);

    Employee login(Map<String,Object> map);

}
