package com.zxyt.ocpp.publish.service.app;

import com.alibaba.fastjson.JSONObject;

public interface IAppService {
    void app(JSONObject json);

    void sendApp(JSONObject json);
}
