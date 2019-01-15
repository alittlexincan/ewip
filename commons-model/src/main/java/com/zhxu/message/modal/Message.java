package com.zhxu.message.modal;

import lombok.Data;

import java.util.List;

@Data
public class Message {
    private Area area;
    private String content;
    private List<File> files;
    private Channel channel;
    private List<Group> groups;
}
