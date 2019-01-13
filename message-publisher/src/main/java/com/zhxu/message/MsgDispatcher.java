package com.zhxu.message;

import com.zhxu.message.modal.Message;

public interface MsgDispatcher {
    void dispatch(Message[] messages);
}
