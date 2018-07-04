package com.hyt.server.entity.sys;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright (C), 2015-2018
 * FileName: User
 * Author:   JiangXincan
 * Date:     2018-4-30 10:44
 * Description: 用户实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "User",description = "用户信息")

@Table(name = "user")
public class User{

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

    @Column(name = "sex",length = 1)
    private Integer sex;

    @Column(name = "phone",length = 11)
    private String phone;

    @Column(name = "area_id",length = 36)
    private String areaId;


    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;


    public User() {
    }

    public User(String loginName, String loginPassword, String name, Integer sex, String phone, String areaId, Date createTime) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.areaId = areaId;
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

    public Integer getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public String getAreaId() {
        return areaId;
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

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Date getCreateTime() {
        if(createTime == null){
            createTime = new Date();
        }
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
