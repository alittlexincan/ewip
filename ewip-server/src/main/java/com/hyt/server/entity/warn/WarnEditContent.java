package com.hyt.server.entity.warn;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright (C), 2015-2018
 * FileName: Area
 * Author:   JiangXincan
 * Date:     2018-4-30 10:44
 * Description: 预警编辑实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "WarnEditContent",description = "预警编辑内容信息")
@Table(name = "warn_edit_content")
public class WarnEditContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "warn_edit_id",length = 64)
    private String warnEditId;

    @Column(name = "channel_id",length = 64)
    private String channelId;

    @Column(name = "area_id",length = 64)
    private String areaId;

    @Column(name = "content",length = 2000)
    private String content;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;


    private String channelName;

    private String channelCode;

    private String areaName;

    private String areaCode;

    public WarnEditContent() {
    }

    public WarnEditContent(String warnEditId, String channelId, String areaId, String content, Date createTime, String channelName, String channelCode, String areaName, String areaCode) {
        this.warnEditId = warnEditId;
        this.channelId = channelId;
        this.areaId = areaId;
        this.content = content;
        this.createTime = createTime;
        this.channelName = channelName;
        this.channelCode = channelCode;
        this.areaName = areaName;
        this.areaCode = areaCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarnEditId() {
        return warnEditId;
    }

    public void setWarnEditId(String warnEditId) {
        this.warnEditId = warnEditId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
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

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
