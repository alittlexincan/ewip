package com.zhxu.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChannelType {
    SMS("SMS", "短信"),
    WECHAT("WECHAT", "微信"),
    EMAIL("EMAIL", "邮件"),
    APP("APP", "手机app"),
    WEB("WEB", "网站"),
    TENCENT_WEIBO("TENCENT_WEIBO", "腾讯微博"),
    SINA_WEIBO("SINA_WEIBO", "新浪微博"),
    BROADCAST("BROADCAST", "广播"),
    FAX("FAX", "传真"),
    TV("TV", "电视"),
    LED("LED", "显示屏"),
    SPEAKER("SPEAKER", "大喇叭"),
    VOICE("VOICE", "语音"),
    RECORD("RECORD", "国突"),
    BEIDOU("BEIDOU", "北斗");

    private String type;
    private String name;
}