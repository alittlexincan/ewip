package com.zxyt.ocpp.client.mapper.sys;

import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.sys.Strategy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("strategyMapper")
public interface IStrategyMapper extends IBaseMapper<Strategy>{

    List<Strategy> findAll(Map<String, Object> map);

    Strategy selectConfig(Map<String, Object> map);
}
