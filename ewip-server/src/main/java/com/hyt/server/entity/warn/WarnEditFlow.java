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

@ApiModel(value = "WarnEditFlow",description = "预警编辑流程信息")
@Table(name = "warn_edit_flow")
public class WarnEditFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "warn_edit_id",length = 64)
    private String warnEditId;

    @Column(name = "flow",length = 2)
    private Integer flow;

    @Column(name = "organization_id",length = 64)
    private String organizationId;

    @Column(name = "organization_name",length = 100)
    private String organizationName;

    @Column(name = "employee_id",length = 64)
    private String employeeId;

    @Column(name = "employee_name",length = 50)
    private String employeeName;

    @Column(name = "advice",length = 2000)
    private String advice;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    public WarnEditFlow() {
    }

    public WarnEditFlow(String warnEditId, Integer flow, String organizationId, String organizationName, String employeeId, String employeeName, String advice) {
        this.warnEditId = warnEditId;
        this.flow = flow;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.advice = advice;
    }

    public WarnEditFlow(String warnEditId, Integer flow, String organizationId, String organizationName, String employeeId, String employeeName, String advice, Date createTime) {
        this.warnEditId = warnEditId;
        this.flow = flow;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.advice = advice;
        this.createTime = createTime;
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

    public Integer getFlow() {
        return flow;
    }

    public void setFlow(Integer flow) {
        this.flow = flow;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
