package com.hyt.server.service.impl.sys;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.sys.Organization;
import com.hyt.server.entity.sys.UserGroup;
import com.hyt.server.mapper.sys.IUserGroupMapper;
import com.hyt.server.service.sys.IUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("userGroupService")
public class UserGroupServiceImpl extends AbstractService<UserGroup> implements IUserGroupService {

    @Autowired
    private IUserGroupMapper userGroupMapper;

    @Override
    public PageInfo<UserGroup> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<UserGroup> areaList = this.userGroupMapper.findAll(map);
        return new PageInfo<>(areaList);
    }

    @Override
    public UserGroup selectById(String id){
        return this.userGroupMapper.selectById(id);
    }


    /**
     * 查询群组信息
     * @param map
     * @return
     */

    @Override
    public JSONObject selectGroup(Map<String, Object> map) {
        JSONObject json=new JSONObject();
        List<UserGroup> list=userGroupMapper.selectGroup(map);
        json.put("list",list);
        return json;
    }

}
