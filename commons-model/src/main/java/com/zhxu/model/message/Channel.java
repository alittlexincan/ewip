package com.zhxu.model.message;

import lombok.Data;

@Data
public class Channel {
    private String id;
    private String name;
    private ChannelType Type;
}
