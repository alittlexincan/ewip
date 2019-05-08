package com.hyt.server.mapper.info;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.info.VfInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository("vfInfoMapper")
public interface IVfInfoMapper extends IBaseMapper<VfInfo> {
    /**
     * 分页查询信息
     * @param map
     * @return
     */
    List<VfInfo> findAll(Map<String, Object> map);

    int importData(List<VfInfo> listNew);
}