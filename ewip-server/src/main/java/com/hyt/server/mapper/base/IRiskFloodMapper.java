package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.RiskFlood;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("riskFloodMapper")
public interface IRiskFloodMapper extends IBaseMapper<RiskFlood> {
    /**
     * 分页查询中小河流信息
     * @param map
     * @return
     */
    List<RiskFlood> findAll(Map<String, Object> map);
}