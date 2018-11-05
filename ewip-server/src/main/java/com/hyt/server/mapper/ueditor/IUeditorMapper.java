package com.hyt.server.mapper.ueditor;

import java.util.List;
import java.util.Map;

/**
 * @Auther: lxv
 * @Date: 2018/10/31 15:23
 * @Description:
 */
public interface IUeditorMapper {

    int insert(Map<String, Object> map);

    List<Map<String,Object>> selectUser(Map<String,Object> map);
}
