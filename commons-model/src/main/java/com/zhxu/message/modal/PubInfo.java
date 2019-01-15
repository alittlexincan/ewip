package com.zhxu.message.modal;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PubInfo {
    private String title;
    private String type;

    private String record;

    private Area area;
    private String organization;
    private Date time;

    private List<Message> messages;
}
