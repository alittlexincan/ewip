package com.hyt.server.service.sys;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.universal.IBaseService;
import com.hyt.server.entity.sys.UserGroup;

import java.util.List;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 群组管理接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IUserGroupService extends IBaseService<UserGroup> {

    PageInfo<UserGroup> selectAll(Map<String, Object> map);

    UserGroup selectById(String id);

    JSONObject selectGroup(Map<String,Object> map);

    JSONObject downModel(Map<String,Object> map);

    JSONObject importData(Map<String,Object> map, List<Map<String,Object>> list);
}
