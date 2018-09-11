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
 * Description: 一键发布内容实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "MessageContent",description = "一键发布内容实体类")
@Table(name = "message_content")
public class MessageContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "message_id",length = 64)
    private String messageId;

    @Column(name = "area_id",length = 64)
    private String areaId;

    @Column(name = "channel_id",length = 64)
    private String channelId;

    @Column(name = "content",length = 2000)
    private String content;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    public MessageContent() {}

    public MessageContent(String messageId, String areaId, String channelId, String content, Date createTime) {
        this.messageId = messageId;
        this.areaId = areaId;
        this.channelId = channelId;
        this.content = content;
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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
