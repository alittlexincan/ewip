package com.zhxu.message.model.sms;

import lombok.Data;

@Data
public class SmsConfig {
    private String type;
    private String areaId;
    private String channelCode;
    private String organizationId;
    private String organizationName;
    private String loginName;
    private String loginPassword;
    private String sign;
    private String authorizeUserName;
    private String authorizeUserPassword;
    private String authorizeUrl;
    private String smsSendUrl;
    private Integer smsNumber;
}
