package com.hyt.server.entity.sys;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright (C), 2015-2018
 * FileName: Area
 * Author:   JiangXincan
 * Date:     2018-4-30 10:44
 * Description: 地区实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "Strategy",description = "预警配置")
@Table(name = "strategy")
public class Strategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name = "area_id",length = 64)
    private String areaId;

    @Column(name = "organization_id",length = 64)
    private String organizationId;

    @Column(name = "disaster_id",length = 64)
    private String disasterId;

    @Column(name = "disaster_name",length = 50)
    private String disasterName;

    @Column(name = "disaster_color",length = 1)
    private int disasterColor;

    @Column(name = "disaster_level",length = 1)
    private int disasterLevel;

    @Column(name = "flow",length = 50)
    private String flow;

    @Column(name = "channel_id",length = 500)
    private String channelId;

    @Column(name = "measure",length = 2000)
    private String measure;

    @Column(name = "instruction",length = 2000)
    private String instruction;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    private String areaName;

    private String organizationName;

    public Strategy() {
    }

    public Strategy(String name, String areaId, String organizationId, String disasterId, String disasterName, int disasterColor, int disasterLevel, String flow, String channelId, String measure, String instruction, Date createTime, String areaName, String organizationName) {
        this.name = name;
        this.areaId = areaId;
        this.organizationId = organizationId;
        this.disasterId = disasterId;
        this.disasterName = disasterName;
        this.disasterColor = disasterColor;
        this.disasterLevel = disasterLevel;
        this.flow = flow;
        this.channelId = channelId;
        this.measure = measure;
        this.instruction = instruction;
        this.createTime = createTime;
        this.areaName = areaName;
        this.organizationName = organizationName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(String disasterId) {
        this.disasterId = disasterId;
    }

    public String getDisasterName() {
        return disasterName;
    }

    public void setDisasterName(String disasterName) {
        this.disasterName = disasterName;
    }

    public int getDisasterColor() {
        return disasterColor;
    }

    public void setDisasterColor(int disasterColor) {
        this.disasterColor = disasterColor;
    }

    public int getDisasterLevel() {
        return disasterLevel;
    }

    public void setDisasterLevel(int disasterLevel) {
        this.disasterLevel = disasterLevel;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
