package com.hyt.server.mapper.sys;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.sys.Channel;
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
@Repository("channelMapper")
public interface IChannelMapper extends IBaseMapper<Channel> {

    /**
     * 分页查询渠道手段信息
     * @param map
     * @return
     */
    List<Channel> findAll(Map<String, Object> map);

    /**
     * 根据地区id查询渠道手段信息
     * @param id
     * @return
     */
    Channel selectById(@Param(value = "id") String id);

    List<Channel> selectByParam(Map<String, Object> map);

}
