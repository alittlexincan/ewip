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
    private String content;
    private String template;

    /**
     * EWIP 相关信息
     */
    private Area area;

    /**
     * 是否对接国突标识
     */
    private String record;

    /**
     * 对接国突需要的参数
     */

    /**
     * 预警信息发布单位ID
     */
    private Date organizationCode;

    /**
     * 预警信息的录入时间
     */
    private Date editTime;

    /**
     * 预警信息发布单位名称
     */
    private String organizationName;

    /**
     * 预警信息的发布时间
     */
    private Date sendTime;

    /**
     * 预警信息类型
     */
    private String warnType;

    /**
     * 预警信息状态
     */
    private String msgType;

    /**
     * 预警信息的发布范围
     */
    private String scope;

    /**
     * 预警事件类型编码
     */
    private String disasterCode;

    /**
     * 预警事件的严重程度
     */
    private String disasterLevel;

    /**
     * 预警信息的签发时间
     */
    private Date effective;

    /**
     * 发布单位签发员
     */
    private String employeeName;

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
