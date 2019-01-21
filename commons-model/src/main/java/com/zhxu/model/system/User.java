package com.zhxu.model.system;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private boolean active;
}
