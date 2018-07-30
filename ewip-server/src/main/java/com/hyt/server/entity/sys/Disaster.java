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
 * Description: 灾种实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "Disaster",description = "灾种信息")
@Table(name = "disaster")
public class Disaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "name",length = 50)
    private String name;

    @Column(name = "code",length = 20)
    private String code;

    @Column(name = "p_id",length = 64)
    private String pId;

    @Column(name = "icon",length = 50)
    private String icon;

    @Column(name = "type",length = 1)
    private int type;

    @Column(name = "disaster_color",length = 1)
    private int disasterColor;

    @Column(name = "disaster_level",length = 1)
    private int disasterLevel;

    @Column(name = "is_config",length = 1)
    private int isConfig;

    @Column(name = "is_strategy",length = 1)
    private int isStrategy;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    private int child;

    private String parentName;

    public Disaster() {
    }

    public Disaster(String name, String code, String pId, String icon, int type, int disasterColor, int disasterLevel, int isConfig, int isStrategy, Date createTime, int child, String parentName) {
        this.name = name;
        this.code = code;
        this.pId = pId;
        this.icon = icon;
        this.type = type;
        this.disasterColor = disasterColor;
        this.disasterLevel = disasterLevel;
        this.isConfig = isConfig;
        this.isStrategy = isStrategy;
        this.createTime = createTime;
        this.child = child;
        this.parentName = parentName;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getIsConfig() {
        return isConfig;
    }

    public void setIsConfig(int isConfig) {
        this.isConfig = isConfig;
    }

    public int getIsStrategy() {
        return isStrategy;
    }

    public void setIsStrategy(int isStrategy) {
        this.isStrategy = isStrategy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
