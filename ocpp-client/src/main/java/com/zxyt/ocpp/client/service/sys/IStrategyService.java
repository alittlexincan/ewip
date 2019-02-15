package com.zxyt.ocpp.client.service.sys;

import com.github.pagehelper.PageInfo;
import com.zxyt.ocpp.client.config.common.universal.IBaseService;
import com.zxyt.ocpp.client.entity.sys.Strategy;

import java.util.Map;

public interface IStrategyService extends IBaseService<Strategy> {

    PageInfo<Strategy> selectAll(Map<String, Object> map);


    Strategy selectConfig(Map<String, Object> map);
}
