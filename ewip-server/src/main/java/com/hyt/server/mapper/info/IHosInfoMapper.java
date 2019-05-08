package com.hyt.server.mapper.info;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.info.HosInfo;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository("hosInfoMapper")
public interface IHosInfoMapper extends IBaseMapper<HosInfo> {
    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<HosInfo> findAll(Map<String, Object> map);
    int importData(List<HosInfo> listNew);
}