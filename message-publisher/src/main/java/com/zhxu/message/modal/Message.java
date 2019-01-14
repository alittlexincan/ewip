package com.zhxu.message.modal;

import lombok.Data;

import java.io.File;

@Data
public class Message {
    private Area area;
    private String content;
    private File[] files;
    private Channel channel;
    private Group[] groups;
}
