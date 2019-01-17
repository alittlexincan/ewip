package com.zhxu.model.message;

import lombok.Data;

import java.util.List;

@Data
public class Group {
    private String id;
    private String name;

    private List<User> users;
}
