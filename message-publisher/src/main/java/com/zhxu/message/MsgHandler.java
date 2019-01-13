package com.zhxu.message;

import com.zhxu.message.modal.Message;

public interface MsgHandler {
    void handle(Message msg);
}
