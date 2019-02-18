package com.zxyt.ocpp.client.service.warn;

import com.alibaba.fastjson.JSONObject;
import com.zxyt.ocpp.client.config.common.universal.IBaseService;
import com.zxyt.ocpp.client.entity.warn.WarnEdit;

public interface IWarnEditService extends IBaseService<WarnEdit> {

    JSONObject insert(JSONObject json);

}
