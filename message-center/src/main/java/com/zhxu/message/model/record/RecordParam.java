package com.zhxu.message.model.record;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RecordParam {

    private String host;
    private int port;
    private String user;
    private String password;
    private String title;
    private String content;


}
