package com.zhxu.message.model.wechat;

import lombok.Data;

@Data
public class WechatConfig {

    private String url;
    private int port;
    private String type;
    private String appId;
    private String areaId;
    private int number;
    private String okUser;
    private String tokenUrl;
    private String appSecret;
    private String channelCode;
    private String templateUrl;
    private String userListUrl;
    private String organizationId;
    private String serviceTemplate;

}
