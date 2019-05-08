package com.hyt.server.mapper.info;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.info.FeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository("feInfoMapper")
public interface IFeInfoMapper extends IBaseMapper<FeInfo> {
    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<FeInfo> findAll(Map<String, Object> map);

    int importData(List<FeInfo> listNew);
}