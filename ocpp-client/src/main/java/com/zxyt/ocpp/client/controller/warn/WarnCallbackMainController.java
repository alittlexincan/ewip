package com.zxyt.ocpp.client.controller.warn;

import com.zxyt.ocpp.client.service.publish.IPublishService;
import com.zxyt.ocpp.client.service.warn.IWarnCallbackMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warnCallbackMain")
public class WarnCallbackMainController {
    @Autowired
    private IWarnCallbackMainService warnCallbackMainService;
}
