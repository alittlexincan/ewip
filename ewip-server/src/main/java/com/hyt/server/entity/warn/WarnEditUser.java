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

@ApiModel(value = "WarnEditUser",description = "预警编辑发布对象信息")
@Table(name = "warn_edit_user")
public class WarnEditUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "warn_edit_id",length = 64)
    private String warnEditId;

    @Column(name = "channel_id",length = 64)
    private String channelId;

    @Column(name = "user_group_id",length = 64)
    private String userGroupId;

    @Column(name = "user_group_name",length = 100)
    private String userGroupName;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    private String userName;

    private String userCode;

    private Double longitude;

    private Double latitude;

    private Double altitude;

    private String channelName;

    public WarnEditUser() {
    }


    public WarnEditUser(String warnEditId, String channelId, String userGroupId, String userGroupName, Date createTime, String userName, String userCode, Double longitude, Double latitude, Double altitude, String channelName) {
        this.warnEditId = warnEditId;
        this.channelId = channelId;
        this.userGroupId = userGroupId;
        this.userGroupName = userGroupName;
        this.createTime = createTime;
        this.userName = userName;
        this.userCode = userCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.channelName = channelName;
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

    public String getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
