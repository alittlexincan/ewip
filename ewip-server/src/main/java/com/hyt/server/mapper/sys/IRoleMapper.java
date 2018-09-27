package com.hyt.server.mapper.sys;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.sys.Employee;
import com.hyt.server.entity.sys.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: XincanJiang
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("roleMapper")
public interface IRoleMapper extends IBaseMapper<Role> {

    /**
     * 查询角色列表信息
     * @param map
     * @return
     */
    List<Role> findAll(Map<String, Object> map);

    /**
     * 根据用户查询用户拥有的角色
     * @param map
     * @return
     */
    List<Role> findRoleByEmployeeId(Map<String, Object> map);

}
