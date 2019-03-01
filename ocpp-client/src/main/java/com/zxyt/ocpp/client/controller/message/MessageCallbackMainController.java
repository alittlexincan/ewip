package com.zxyt.ocpp.client.controller.message;

import com.zxyt.ocpp.client.service.message.IMessageCallbackMainService;
import com.zxyt.ocpp.client.service.message.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messageCallbackMain")
public class MessageCallbackMainController {

    @Autowired
    private IMessageCallbackMainService messageCallbackMainService;
}
