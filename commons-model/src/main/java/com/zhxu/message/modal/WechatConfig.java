package com.zhxu.message.modal;

import lombok.Data;

@Data
public class WechatConfig {
    private String appId;
    private String appSecret;
    private String tokenUrl;
    private String serviceTemplate;
    private String templateUrl;
    private String userListUrl;
    private String okUser;
    private Integer number;
}
