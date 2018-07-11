package com.hyt.server.entity.sys;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright (C), 2015-2018
 * FileName: Employee
 * Author:   JiangXincan
 * Date:     2018-4-30 10:44
 * Description: 员工实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "Employee",description = "员工信息")
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 36)
    private String id;

    @Column(name = "login_name",length = 25)
    private String loginName;

    @Column(name = "login_password",length = 64)
    private String loginPassword;

    @Column(name = "name",length = 25)
    private String name;

    @Column(name = "area_id",length = 64)
    private String areaId;

    @Column(name = "area_name",length = 50)
    private String areaName;

    @Column(name = "area_level",length = 1)
    private String areaLevel;

    @Column(name = "org_id",length = 64)
    private String orgId;

    @Column(name = "org_name",length = 50)
    private String orgName;

    @Column(name = "phone",length = 11)
    private String phone;

    @Column(name = "email",length = 50)
    private String email;

    @Column(name = "sex",length = 1)
    private Integer sex;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;


    public Employee() {
    }

    public Employee(String loginName, String loginPassword, String name, String areaId, String areaName, String areaLevel, String orgId, String orgName, String phone, String email, Integer sex, Date createTime) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.name = name;
        this.areaId = areaId;
        this.areaName = areaName;
        this.areaLevel = areaLevel;
        this.orgId = orgId;
        this.orgName = orgName;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getName() {
        return name;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getSex() {
        return sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
