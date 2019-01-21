package com.zhxu.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChannelType {
    SMS("短信"),
    WECHAT("微信"),
    EMAIL("邮件"),
    APP("手机app"),
    WEB("网站"),
    TENCENT_WEIBO("腾讯微博"),
    WEIBO("新浪微博"),
    BROADCAST("广播"),
    FAX("传真"),
    TV("电视"),
    LED("显示屏"),
    SPEAKER("大喇叭"),
    VOICE("语音"),
    RECORD("国突"),
    BEIDOU("北斗");

    private String name;
}
