package com.zhxu.message.model.wechat;

import com.zhxu.model.message.Message;
import lombok.Data;

@Data
public class WechatMessage extends Message {
    private String title;
    private String template;
}
