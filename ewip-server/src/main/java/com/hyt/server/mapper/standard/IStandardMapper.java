package com.hyt.server.mapper.standard;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.base.UnitSchool;
import com.hyt.server.entity.standard.Standard;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: lixiaowei
 * @Description:
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Repository("standardMapper")
public interface IStandardMapper extends IBaseMapper<Standard> {
    /**
     * 分页查询学校信息
     * @param map
     * @return
     */
    List<Standard> findAll(Map<String, Object> map);
}