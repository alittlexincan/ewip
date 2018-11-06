package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.RiskWaterloggingArea;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("riskWaterloggingAreaMapper")
public interface IRiskWaterloggingAreaMapper extends IBaseMapper<RiskWaterloggingArea> {
    /**
     * 分页查询易涝区信息
     * @param map
     * @return
     */
    List<RiskWaterloggingArea> findAll(Map<String, Object> map);
}