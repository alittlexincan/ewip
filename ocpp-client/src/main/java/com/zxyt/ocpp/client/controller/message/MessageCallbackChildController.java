package com.zxyt.ocpp.client.controller.message;

import com.zxyt.ocpp.client.service.message.IMessageCallbackChildService;
import com.zxyt.ocpp.client.service.message.IMessageCallbackMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messageCallbackChild")
public class MessageCallbackChildController {
    @Autowired
    private IMessageCallbackChildService messageCallbackChildService;
}
