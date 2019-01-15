package com.zhxu.message.modal;

import lombok.Data;

import java.util.List;

@Data
public class Group {
    private String id;
    private String name;

    private List<User> users;
}
