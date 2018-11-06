package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.RiskWaterlogging;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("riskWaterloggingMapper")
public interface IRiskWaterloggingMapper extends IBaseMapper<RiskWaterlogging> {
    /**
     * 分页查询内涝隐患点信息
     * @param map
     * @return
     */
    List<RiskWaterlogging> findAll(Map<String, Object> map);
}