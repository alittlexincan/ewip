package com.hyt.server.mapper.info;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.info.RsvInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository("rsvInfoMapper")
public interface IRsvInfoMapper extends IBaseMapper<RsvInfo> {
    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<RsvInfo> findAll(Map<String, Object> map);

    int importData(List<RsvInfo> listNew);
}