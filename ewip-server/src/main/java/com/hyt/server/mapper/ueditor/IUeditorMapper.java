package com.hyt.server.mapper.ueditor;

import com.hyt.server.entity.sys.User;

import java.util.List;
import java.util.Map;

/**
 * @Auther: lxv
 * @Date: 2018/10/31 15:23
 * @Description:
 */
public interface IUeditorMapper {

    int insert(Map<String, Object> map);

    List<User> selectUser(Map<String,Object> map);
}
