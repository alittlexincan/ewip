package com.zxyt.ocpp.client.mapper.sys;

import com.alibaba.fastjson.JSONObject;
import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.sys.WarnConfig;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("warnConfigMapper")
public interface IWarnConfigMapper extends IBaseMapper<WarnConfig> {

    List<WarnConfig> findAll(Map<String, Object> map);

    WarnConfig selectConfig(Map<String, Object> map);
}
