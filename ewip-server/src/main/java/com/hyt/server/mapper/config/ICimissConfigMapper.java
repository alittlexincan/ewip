package com.hyt.server.mapper.config;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.config.CimissConfig;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ICimissConfigMapper
 * @Description 从数据库读取Cimiss的信息
 * @Author Xincan
 * @Version 1.0
 **/
@Repository("cimissConfigMapper")
public interface ICimissConfigMapper extends IBaseMapper<CimissConfig> {
    /**
     * 根据条件分页查询Cimiss信息
     * @param map
     * @return
     */
    List<CimissConfig> findAll(Map<String, Object> map);
}
