package com.zhxu.message.model.email;

import lombok.Data;

@Data
public class EmailConfig {
    private String host;
    private int port;
    private String type;
    private String areaId;
    private String password;
    private String userName;
    private String channelCode;
    private String organizationId;
}
