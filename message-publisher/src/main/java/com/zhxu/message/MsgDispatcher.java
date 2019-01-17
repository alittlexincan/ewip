package com.zhxu.message;

import com.zhxu.model.message.Message;

import java.util.List;

public interface MsgDispatcher {
    void dispatch(List<Message> messages);
}
