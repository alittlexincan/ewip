package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.DisasterHistory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-1
 * @Modified By:
 */
@Repository("disasterHistoryMapper")
public interface IDisasterHistoryMapper extends IBaseMapper<DisasterHistory> {

    /**
     * 分页查询历史灾情信息
     * @param map
     * @return
     */
    List<DisasterHistory> findAll(Map<String, Object> map);

}