package com.zhxu.message.modal;

import lombok.Data;

import java.util.Date;

@Data
public class PubInfo {
    private String title;
    private String type;
    private Date time;
    private String organization;

    private Message[] messages;
}
