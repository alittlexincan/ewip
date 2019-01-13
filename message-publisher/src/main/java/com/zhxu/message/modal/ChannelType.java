package com.zhxu.message.modal;

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
    BEIDOU("BEIDOU", "北斗");



    private String type;
    private String name;

    ChannelType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
