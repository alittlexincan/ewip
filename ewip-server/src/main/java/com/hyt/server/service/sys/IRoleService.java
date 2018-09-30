package com.hyt.server.service.sys;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.sys.Employee;
import com.hyt.server.entity.sys.Permission;
import com.hyt.server.entity.sys.Role;

import java.util.List;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 员工接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IRoleService extends IBaseService<Role> {

    /**
     * 分页查询角色信息
     * @param map
     * @return
     */
    PageInfo<Role> selectAll(Map<String, Object> map);

    List<Role> selectById(Map<String, Object> map);

    /**
     * 查询角色信息
     * @param map
     * @return
     */
    List<Role> selectByRoleName(Map<String, Object> map);

    /**
     * 角色配置权限
     * @param map
     * @return
     */
    int insertRolePermission(Map<String, Object> map);

    /**
     * 根据角色ID查询对应的权限信息
     * @param map
     * @return
     */
    List<Permission> selectRoleInPermission(Map<String, Object> map);

}
