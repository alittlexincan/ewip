package com.zxyt.ocpp.client.service.impl.warn;

import com.zxyt.ocpp.client.config.common.universal.AbstractService;
import com.zxyt.ocpp.client.entity.warn.WarnCallbackMain;
import com.zxyt.ocpp.client.entity.warn.WarnEdit;
import com.zxyt.ocpp.client.mapper.warn.IWarnCallbackMainMapper;
import com.zxyt.ocpp.client.mapper.warn.IWarnEditMapper;
import com.zxyt.ocpp.client.service.warn.IWarnCallbackMainService;
import com.zxyt.ocpp.client.service.warn.IWarnEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("warnCallbackMainService")
public class WarnCallbackMainServiceImpl extends AbstractService<WarnCallbackMain> implements IWarnCallbackMainService {
    @Autowired
    private IWarnCallbackMainMapper warnCallbackMainMapper;
}
