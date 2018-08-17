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

@ApiModel(value = "WarnEdit",description = "预警编辑信息")
@Table(name = "warn_edit")
public class WarnEdit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "title",length = 100)
    private String title;

    @Column(name = "area_id",length = 64)
    private String areaId;

    @Column(name = "organization_id",length = 64)
    private String organizationId;

    @Column(name = "disaster_id",length = 64)
    private String disasterId;

    @Column(name = "disaster_name",length = 100)
    private String disasterName;

    @Column(name = "disaster_color",length = 1)
    private Integer disasterColor;

    @Column(name = "disaster_level",length = 1)
    private Integer disasterLevel;

    @Column(name = "warn_type",length = 20)
    private String warnType;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "edit_time")
    private Date editTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "forecast_time")
    private Date forecastTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "invalid_time")
    private Date invalidTime;

    @Column(name = "record",length = 1)
    private Integer record;

    @Column(name = "measure",length = 2000)
    private String measure;

    @Column(name = "instruction",length = 2000)
    private String instruction;

    @Column(name = "flow",length = 50)
    private String flow;

    @Column(name = "current_flow",length = 2)
    private Integer currentFlow;

    @Column(name = "next_flow",length = 2)
    private Integer nextFlow;

    @Column(name = "status",length = 1)
    private Integer status;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "send_time")
    private Date sendTime;

    public WarnEdit() {
    }

    public WarnEdit(String title, String areaId, String organizationId, String disasterId, String disasterName, Integer disasterColor, Integer disasterLevel, String warnType, Date editTime, Date forecastTime, Date invalidTime, Integer record, String measure, String instruction, String flow, Integer currentFlow, Integer nextFlow, Integer status, Date createTime, Date sendTime) {
        this.title = title;
        this.areaId = areaId;
        this.organizationId = organizationId;
        this.disasterId = disasterId;
        this.disasterName = disasterName;
        this.disasterColor = disasterColor;
        this.disasterLevel = disasterLevel;
        this.warnType = warnType;
        this.editTime = editTime;
        this.forecastTime = forecastTime;
        this.invalidTime = invalidTime;
        this.record = record;
        this.measure = measure;
        this.instruction = instruction;
        this.flow = flow;
        this.currentFlow = currentFlow;
        this.nextFlow = nextFlow;
        this.status = status;
        this.createTime = createTime;
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

    public Integer getDisasterColor() {
        return disasterColor;
    }

    public void setDisasterColor(Integer disasterColor) {
        this.disasterColor = disasterColor;
    }

    public Integer getDisasterLevel() {
        return disasterLevel;
    }

    public void setDisasterLevel(Integer disasterLevel) {
        this.disasterLevel = disasterLevel;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Date getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(Date forecastTime) {
        this.forecastTime = forecastTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
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

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public Integer getCurrentFlow() {
        return currentFlow;
    }

    public void setCurrentFlow(Integer currentFlow) {
        this.currentFlow = currentFlow;
    }

    public Integer getNextFlow() {
        return nextFlow;
    }

    public void setNextFlow(Integer nextFlow) {
        this.nextFlow = nextFlow;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
