package com.hyt.server.entity.sys;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "Zhuce",description = "信息")
@Table(name = "zhuce")
public class Zhuce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "employee_id",length = 64)
    private String employeeId;

    @Column(name = "type",length = 50)
    private String type;

    @Column(name = "area",length = 12)
    private String area;

    @Column(name = "disasterType",length = 64)
    private String disasterType;

    @Column(name = "disaster",length = 64)
    private String disaster;

    @Column(name = "org",length = 64)
    private String org;

    @Column(name = "issuse",length = 64)
    private Integer issuse;


    @Column(name = "description",length = 64)
    private String description;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private String createTime;

    private String loginName;

    private String userName;

    public Zhuce() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getDisaster() {
        return disaster;
    }

    public void setDisaster(String disaster) {
        this.disaster = disaster;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public Integer getIssuse() {
        return issuse;
    }

    public void setIssuse(Integer issuse) {
        this.issuse = issuse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Zhuce(String employeeId, String type, String area, String disasterType, String disaster, String org, Integer issuse, String description, String createTime, String loginName, String userName) {
        this.employeeId = employeeId;
        this.type = type;
        this.area = area;
        this.disasterType = disasterType;
        this.disaster = disaster;
        this.org = org;
        this.issuse = issuse;
        this.description = description;
        this.createTime = createTime;
        this.loginName = loginName;
        this.userName = userName;
    }
}
