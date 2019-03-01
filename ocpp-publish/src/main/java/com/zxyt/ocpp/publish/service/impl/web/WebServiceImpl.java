package com.zxyt.ocpp.publish.service.impl.web;

import com.alibaba.fastjson.JSONObject;
import com.zxyt.ocpp.publish.service.web.IWebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service("webService")
public class WebServiceImpl implements IWebService {

    @Override
    @Async
    public void web(JSONObject json) {

    }

    @Override
    @Async
    public void sendWeb(JSONObject json) {

    }
}
