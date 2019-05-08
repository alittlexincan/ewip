package com.hyt.server.mapper.info;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.info.BiInfo;
import com.hyt.server.entity.info.GeoInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository("geoInfoMapper")
public interface IGeoInfoMapper extends IBaseMapper<GeoInfo> {
    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<GeoInfo> findAll(Map<String, Object> map);

    int importData(List<GeoInfo> listNew);

    List<Map<String,Object>> selectDisasterList(Map<String,Object> map);
}