package com.zhxu.message.model.sms;

import com.zhxu.model.message.Message;
import lombok.Data;

import java.util.Set;

@Data
public class SmsMessage extends Message {
    private Set<String> mobiles;
}
