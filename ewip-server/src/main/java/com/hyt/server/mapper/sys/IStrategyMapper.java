package com.hyt.server.mapper.sys;

import com.hyt.server.config.common.universal.IBaseMapper;
import com.hyt.server.entity.sys.Strategy;
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
@Repository("strategyMapper")
public interface IStrategyMapper extends IBaseMapper<Strategy> {

    /**
     * 分页查询策略配置信息
     * @param map
     * @return
     */
    List<Strategy> findAll(Map<String, Object> map);

    /**
     * 根据策略id查询策略配置信息
     * @param id
     * @return
     */
    Strategy selectById(@Param(value = "id") String id);

}
