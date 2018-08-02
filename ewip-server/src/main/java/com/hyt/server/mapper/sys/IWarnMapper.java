package com.hyt.server.mapper.sys;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.sys.Warn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:30 2018-4-18
 * @Modified By:
 */
@Repository("warnMapper")
public interface IWarnMapper extends IBaseMapper<Warn> {

    /**
     * 分页查询预警配置信息
     * @param map
     * @return
     */
    List<Warn> findAll(Map<String, Object> map);

    /**
     * 根据地区id查询预警配置信息
     * @param id
     * @return
     */
    Warn selectById(@Param(value = "id") String id);

    /**
     * 多条件查询预警配置信息
     * @param map
     * @return
     */
    Warn selectConfig(Map<String, Object> map);

}
