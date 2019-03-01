package com.zxyt.ocpp.client.mapper.warn;

import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.warn.WarnCallbackMain;
import com.zxyt.ocpp.client.entity.warn.WarnEdit;
import org.springframework.stereotype.Repository;

@Repository("warnCallbackMainMapper")
public interface IWarnCallbackMainMapper extends IBaseMapper<WarnCallbackMain> {
}
