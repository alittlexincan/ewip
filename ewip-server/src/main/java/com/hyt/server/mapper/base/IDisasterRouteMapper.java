package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.DisasterRoute;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("disasterRouteMapper")
public interface IDisasterRouteMapper extends IBaseMapper<DisasterRoute> {
    /**
     * 分页查询历史灾情信息
     * @param map
     * @return
     */
    List<DisasterRoute> findAll(Map<String, Object> map);
}