package com.zxyt.ocpp.publish.service.sina;

import com.alibaba.fastjson.JSONObject;

public interface ISinaWeiBoService {

    void sinaWeiBo(JSONObject json);

    void sendSinaWeiBo(JSONObject json);

}
