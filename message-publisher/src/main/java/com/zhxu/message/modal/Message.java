package com.zhxu.message.modal;

import lombok.Data;

@Data
public class Message {
    private Area area;
    private String content;
    private Channel channel;
    private Group[] groups;
}
