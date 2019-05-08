package com.hyt.server.mapper.info;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.info.BiInfo;
import com.hyt.server.entity.info.MfrInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository("mfrInfoMapper")
public interface IMfrInfoMapper extends IBaseMapper<MfrInfo> {
    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<MfrInfo> findAll(Map<String, Object> map);

    int importData(List<MfrInfo> listNew);
}