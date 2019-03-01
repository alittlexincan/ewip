package com.zxyt.ocpp.client.service.impl.warn;

import com.zxyt.ocpp.client.config.common.universal.AbstractService;
import com.zxyt.ocpp.client.entity.warn.WarnCallbackChild;
import com.zxyt.ocpp.client.entity.warn.WarnCallbackMain;
import com.zxyt.ocpp.client.mapper.warn.IWarnCallbackChildMapper;
import com.zxyt.ocpp.client.mapper.warn.IWarnCallbackMainMapper;
import com.zxyt.ocpp.client.service.warn.IWarnCallbackChildService;
import com.zxyt.ocpp.client.service.warn.IWarnCallbackMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("warnCallbackChildService")
public class WarnCallbackChildServiceImpl extends AbstractService<WarnCallbackChild> implements IWarnCallbackChildService {
    @Autowired
    private IWarnCallbackChildMapper warnCallbackChildMapper;
}
