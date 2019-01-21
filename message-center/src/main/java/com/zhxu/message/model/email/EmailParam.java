package com.zhxu.message.model.email;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class EmailParam {
    private String host;
    private int port;
    private String password;
    private String userName;
    private String title;
    private String content;
    private Set<String> emails;
}
