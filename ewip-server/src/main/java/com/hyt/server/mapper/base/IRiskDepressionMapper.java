package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.RiskDepression;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("riskDepressionMapper")
public interface IRiskDepressionMapper extends IBaseMapper<RiskDepression> {
    /**
     * 分页查询洼地信息
     * @param map
     * @return
     */
    List<RiskDepression> findAll(Map<String, Object> map);
}