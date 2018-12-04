package com.hyt.server.mapper.sys;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.sys.UserGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("userGroupMapper")
public interface IUserGroupMapper extends IBaseMapper<UserGroup> {

    /**
     * 分页查询地区信息
     * @param map
     * @return
     */
    List<UserGroup> findAll(Map<String, Object> map);

    /**
     * 根据地区id查询地区信息
     * @param id
     * @return
     */
    UserGroup selectById(@Param(value = "id") String id);
    /**
     * 查询群组信息
     * @param map
     * @return
     */
    List<UserGroup> selectGroup(Map<String,Object> map);
}
