package com.zhxu.model.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class PublishInfo {
    /**
     * 所选地区
     */
    private List<Area> areas;

    /**
     * 地区对应的内容
     */
    private Map<String, String> contents;

    /**
     * 所选渠道
     */
    private List<Channel> channels;

    /**
     * 渠道对应的组
     */
    private Map<String, List<Group>> groups;

    /**
     * 组对应的用户
     */
    private Map<String, List<User>> users;

    /**
     * 上传附件
     */
    @JSONField(name = "files")
    private List<Attachment> attachments;

    /**
     * OCPP 相关信息
     */
    private String title;
    private String type;
    private String record;
    private String content;
    private String template;

    /**
     * 地区信息
     */
    @Data
    public static class Area {
        @JSONField(name = "areaId")
        private String id;
        @JSONField(name = "areaName")
        private String name;
        @JSONField(name = "areaCode")
        private String code;
    }

    /**
     * 渠道信息
     */
    @Data
    public static class Channel {
        @JSONField(name = "channelId")
        private String id;
        @JSONField(name = "channelName")
        private String name;
        @JSONField(name = "channelCode")
        private ChannelType type;
    }

    /**
     * 组信息
     */
    @Data
    public static class Group {
        @JSONField(name = "userGroupId")
        private String id;
        @JSONField(name = "userGroupName")
        private String name;
    }

    /**
     * 用户信息
     */
    @Data
    public static class User {
        @JSONField(name = "userName")
        private String name;
        @JSONField(name = "channelCode")
        private ChannelType type;
        @JSONField(name = "userCode")
        private String userCode;
    }

    /**
     * 附件信息
     */
    @Data
    public static class Attachment {
        @JSONField(name = "name")
        private String name;
        @JSONField(name = "size")
        private Integer size;
        @JSONField(name = "url")
        private String url;
    }
}
