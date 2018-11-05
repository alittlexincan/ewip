package com.hyt.server.entity.ueditor;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

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
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Ueditor() {

    }
    public Ueditor(Integer type, String title, String path, Date createTime) {
        this.type = type;
        this.title = title;
        this.path = path;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}
