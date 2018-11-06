package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.RiskForest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("riskForestMapper")
public interface IRiskForestMapper extends IBaseMapper<RiskForest> {
    /**
     * 分页查询森林火险灾害点信息
     * @param map
     * @return
     */
    List<RiskForest> findAll(Map<String, Object> map);
}