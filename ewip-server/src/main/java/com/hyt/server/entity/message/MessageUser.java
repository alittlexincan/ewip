package com.hyt.server.entity.message;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright (C), 2015-2018
 * FileName: Area
 * Author:   JiangXincan
 * Date:     2018-4-30 10:44
 * Description: 一键发布受众实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "MessageUser",description = "一键发布受众实体类")
@Table(name = "message_user")
public class MessageUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "message_id",length = 64)
    private String messageId;

    @Column(name = "channel_id",length = 64)
    private String channelId;

    @Column(name = "user_group_id",length = 64)
    private String userGroupId;

    @Column(name = "user_group_name",length = 50)
    private String userGroupName;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    public MessageUser() {}


    public MessageUser(String messageId, String channelId, String userGroupId, String userGroupName, Date createTime) {
        this.messageId = messageId;
        this.channelId = channelId;
        this.userGroupId = userGroupId;
        this.userGroupName = userGroupName;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
