package com.hyt.server.mapper.info;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.info.DsInfo;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


@Repository("dsInfoMapper")
public interface IDsInfoMapper extends IBaseMapper<DsInfo> {
    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<DsInfo> findAll(Map<String, Object> map);

    int importData(List<DsInfo> listNew);
}