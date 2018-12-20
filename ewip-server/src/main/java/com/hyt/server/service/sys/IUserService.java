package com.hyt.server.service.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.sys.User;

import java.util.List;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 受众管理接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IUserService extends IBaseService<User> {

    PageInfo<User> selectAll(Map<String, Object> map);

    User selectById(String id);

    int insertUser(Map<String,Object> map);

    int insertUserJob(Map<String,Object> map);

    int updateUser(Map<String,Object> map);

    int deleteUserJobById(String id);

    int deleteUserJobByIds(String id);

    List<User> selectList(Map<String, Object> map);

    JSONObject userDetails(Map<String,Object> map);

    JSONObject downModel(Map<String,Object> map);

    JSONObject importData(Map<String,Object> map, List<Map<String,Object>> list);
}
