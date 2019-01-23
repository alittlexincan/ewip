package com.zxyt.ocpp.client.entity.ueditor;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 * @Auther: lxv
 * @Date: 2018/10/31 15:10
 * @Description:
 */
@Table(name = "server_product")
public class Ueditor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "type")
    private Integer type;
    @Column(name = "title")
    private String title;
    @Column(name = "path")
    private String path;

    @Column(name = "area_id",length = 64)
    private String areaId;

    @Column(name = "organization_id",length = 64)
    private String organizationId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Ueditor() {

    }

    public Ueditor(Integer type, String title, String path, String areaId, String organizationId, Date createTime) {
        this.type = type;
        this.title = title;
        this.path = path;
        this.areaId = areaId;
        this.organizationId = organizationId;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
