package com.zhxu.message;

import com.zhxu.model.message.Message;

public interface MsgHandler {
    void handle(Message msg);
}
