package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitMarket;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("unitMarketMapper")
public interface IUnitMarketMapper extends IBaseMapper<UnitMarket> {
    /**
     * 分页查询商场信息
     * @param map
     * @return
     */
    List<UnitMarket> findAll(Map<String, Object> map);
}