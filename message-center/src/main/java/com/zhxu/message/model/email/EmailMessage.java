package com.zhxu.message.model.email;

import com.zhxu.model.message.Message;
import lombok.Data;

import java.util.Set;

@Data
public class EmailMessage extends Message {
    private String title;
    private Set<String> emails;
}
