package com.hyt.server.mapper.base;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.FacilityOffice;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * @Author: lxm
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("facilityOfficeMapper")
public interface IFacilityOfficeMapper extends IBaseMapper<FacilityOffice> {
    /**
     * 分页查询办公场所信息
     * @param map
     * @return
     */
    List<FacilityOffice> findAll(Map<String, Object> map);
}