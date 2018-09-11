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
 * Description: 一键发布基础信息实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "Message",description = "一键发布基础信息实体类")
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "title",length = 100)
    private String title;

    @Column(name = "area_id",length = 64)
    private String areaId;

    @Column(name = "area_name",length = 50)
    private String areaName;

    @Column(name = "organization_id",length = 64)
    private String organizationId;

    @Column(name = "organization_name",length = 50)
    private String organizationName;

    @Column(name = "type",length = 1)
    private Integer type;

    @Column(name = "template",length = 1)
    private Integer template;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "send_time")
    private Date sendTime;

    public Message() {}

    public Message(String title, String areaId, String areaName, String organizationId, String organizationName, Integer type, Integer template, Date sendTime) {
        this.title = title;
        this.areaId = areaId;
        this.areaName = areaName;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.type = type;
        this.template = template;
        this.sendTime = sendTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTemplate() {
        return template;
    }

    public void setTemplate(Integer template) {
        this.template = template;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
