package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.DisasterType;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("disasterTypeMapper")
public interface IDisasterTypeMapper extends IBaseMapper<DisasterType> {
    /**
     * 分页查询历史灾情信息
     * @param map
     * @return
     */
    List<DisasterType> findAll(Map<String, Object> map);
}