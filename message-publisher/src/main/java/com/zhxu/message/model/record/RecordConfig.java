package com.zhxu.message.model.record;

import lombok.Data;

@Data
public class RecordConfig {

    private String host;
    private int port;
    private String type;
    private String user;
    private String areaId;
    private String password;
    private String channelCode;
    private String organizationId;

}
