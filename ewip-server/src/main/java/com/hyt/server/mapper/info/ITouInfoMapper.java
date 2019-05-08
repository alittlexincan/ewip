package com.hyt.server.mapper.info;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.info.TouInfo;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository("touInfoMapper")
public interface ITouInfoMapper extends IBaseMapper<TouInfo> {
    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<TouInfo> findAll(Map<String, Object> map);

    int importData(List<TouInfo> listNew);
}