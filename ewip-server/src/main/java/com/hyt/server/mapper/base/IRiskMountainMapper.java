package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.RiskMountain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("riskMountainMapper")
public interface IRiskMountainMapper extends IBaseMapper<RiskMountain> {
    /**
     * 分页查询山洪沟信息
     * @param map
     * @return
     */
    List<RiskMountain> findAll(Map<String, Object> map);
}