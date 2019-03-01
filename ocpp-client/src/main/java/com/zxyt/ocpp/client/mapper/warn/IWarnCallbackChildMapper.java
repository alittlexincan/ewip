package com.zxyt.ocpp.client.mapper.warn;

import com.zxyt.ocpp.client.config.common.universal.IBaseMapper;
import com.zxyt.ocpp.client.entity.warn.WarnCallbackChild;
import com.zxyt.ocpp.client.entity.warn.WarnCallbackMain;
import org.springframework.stereotype.Repository;

@Repository("warnCallbackChildMapper")
public interface IWarnCallbackChildMapper extends IBaseMapper<WarnCallbackChild> {
}
