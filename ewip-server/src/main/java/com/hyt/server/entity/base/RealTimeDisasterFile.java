package com.hyt.server.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright (C), 2015-2018
 * Author:   lxv
 * Description: 实体类
 */

@ApiModel(value = "RealTimeDisasterFile",description = "上传文件信息")
@Table(name = "real_time_disaster_file")
public class RealTimeDisasterFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "warn_edit_id",length = 64)
    private String warnEditId;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name = "size",length = 50)
    private String size;

    @Column(name = "url",length = 200)
    private String url;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    public RealTimeDisasterFile() {
    }

    public RealTimeDisasterFile(String warnEditId, String name, String size, String url, Date createTime) {
        this.warnEditId = warnEditId;
        this.name = name;
        this.size = size;
        this.url = url;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
