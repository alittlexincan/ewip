package com.hyt.server.service.impl.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.sys.Menu;
import com.hyt.server.entity.sys.Permission;
import com.hyt.server.entity.sys.Role;
import com.hyt.server.mapper.sys.IRoleMapper;
import com.hyt.server.service.sys.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: XincanJiang
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("roleService")
public class RoleServiceImpl extends AbstractService<Role> implements IRoleService {

    @Autowired
    private IRoleMapper roleMapper;

    @Override
    public PageInfo<Role> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Role> areaList = this.roleMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public List<Role> selectById(Map<String, Object> map){
        return this.roleMapper.findRoleByEmployeeId(map);
    }

    /**
     * 查询所有角色名称
     * @return
     */
    @Override
    public List<Role> selectByRoleName(Map<String, Object> map){
        return this.roleMapper.selectByRoleName(map);
    }

    /**
     * 角色配置菜单
     *
     * @param map
     * @return
     */
    @Override
    @Transactional
    public int insertRoleMenu(Map<String, Object> map) {
        // 先删除该角色拥有的所有权限，然后在添加当前配置的权限
        this.roleMapper.deleteRoleInMenu(map);
        return  this.roleMapper.insertRoleMenu(map);
    }

    /**
     * 根据角色ID查询对应的菜单信息
     *
     * @param map
     * @return
     */
    @Override
    public List<Menu> selectRoleInMenu(Map<String, Object> map) {
        return this.roleMapper.selectRoleInMenu(map);
    }

    /**
     * 角色配置权限
     *
     * @param map
     * @return
     */
    @Override
    @Transactional
    public int insertRolePermission(Map<String, Object> map) {
        // 先删除该角色拥有的所有权限，然后在添加当前配置的权限
        this.roleMapper.deleteRoleInPermission(map);
        return  this.roleMapper.insertRolePermission(map);
    }

    /**
     * 根据角色ID查询对应的权限信息
     * @param map
     * @return
     */
    @Override
    public List<Permission> selectRoleInPermission(Map<String, Object> map){
        return this.roleMapper.selectRoleInPermission(map);
    }
}
