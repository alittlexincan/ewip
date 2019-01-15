package com.zhxu.message;

import com.zhxu.message.modal.Message;

import java.util.List;

public interface MsgDispatcher {
    void dispatch(List<Message> messages);
}
