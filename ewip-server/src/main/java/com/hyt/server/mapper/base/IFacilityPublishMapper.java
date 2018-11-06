package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.FacilityPublish;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("facilityPublishMapper")
public interface IFacilityPublishMapper extends IBaseMapper<FacilityPublish> {
    /**
     * 分页查询发布设施信息
     * @param map
     * @return
     */
    List<FacilityPublish> findAll(Map<String, Object> map);
}