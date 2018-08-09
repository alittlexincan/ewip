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
 * Description: 预警编辑操作实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "WarnEditOption",description = "预警编辑信息")
public class WarnEditOption {

    @Id
    private String id;

    private String title;

    private String areaId;

    private String organizationId;

    private String disasterId;

    private String disasterName;

    private Integer disasterColor;

    private Integer disasterLevel;

    private String disasterIcon;

    private String warnType;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date editTime;

    private String flow;

    private Integer currentFlow;

    private Integer nextFlow;

    private String employeeName;

    public WarnEditOption() {
    }

    public WarnEditOption(String id, String title, String areaId, String organizationId, String disasterId, String disasterName, Integer disasterColor, Integer disasterLevel, String disasterIcon, String warnType, Date editTime, String flow, Integer currentFlow, Integer nextFlow, String employeeName) {
        this.id = id;
        this.title = title;
        this.areaId = areaId;
        this.organizationId = organizationId;
        this.disasterId = disasterId;
        this.disasterName = disasterName;
        this.disasterColor = disasterColor;
        this.disasterLevel = disasterLevel;
        this.disasterIcon = disasterIcon;
        this.warnType = warnType;
        this.editTime = editTime;
        this.flow = flow;
        this.currentFlow = currentFlow;
        this.nextFlow = nextFlow;
        this.employeeName = employeeName;
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

    public String getDisasterIcon() {
        return disasterIcon;
    }

    public void setDisasterIcon(String disasterIcon) {
        this.disasterIcon = disasterIcon;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
