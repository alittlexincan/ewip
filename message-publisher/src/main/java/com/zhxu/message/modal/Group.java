package com.zhxu.message.modal;

import lombok.Data;

@Data
public class Group {
    private String id;
    private String name;

    private User[] users;
}
