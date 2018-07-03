package com.hyt.server.service.impl.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.sys.User;
import com.hyt.server.mapper.sys.IUserMapper;
import com.hyt.server.service.sys.IUserService;
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
@Service("userService")
public class UserServiceImpl extends AbstractService<User> implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public PageInfo<User> selectAll(Map<String,Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<User> userInfoList = this.userMapper.findAll(map);
        return new PageInfo<>(userInfoList);
    }

}
