package com.hyt.server.mapper.config;


import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.config.ChannelConfig;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("channelConfigMapper")
public interface IChannelConfigMapper extends IBaseMapper<ChannelConfig> {

    /**
     * 分页查询渠道手段信息
     * @param map
     * @return
     */
    List<ChannelConfig> findAll(Map<String, Object> map);

    int deleteByType(Map<String, Object> map);

    ChannelConfig  findConfigByCode();

}
