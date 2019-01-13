package com.zhxu.message;

import com.zhxu.message.modal.SmsParam;

public interface MsgSender {
    void send(SmsParam param);
}
