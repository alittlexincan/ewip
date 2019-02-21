package com.zxyt.ocpp.client.controller.message;

import com.zxyt.ocpp.client.service.message.IMessageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messageFile")
public class MessageFileController {

    @Autowired
    private IMessageFileService messageFileService;
}
