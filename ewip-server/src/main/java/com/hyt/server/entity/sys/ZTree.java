package com.hyt.server.entity.sys;

import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Copyright (C), 2015-2018
 * FileName: ZTree
 * Author:   JiangXincan
 * Date:     2018-4-30 10:44
 * Description: ZTree实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "ZTree",description = "地区树信息")
@Table(name = "area")
public class ZTree {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "p_id")
    private String pId;

    @Column(name = "level")
    private int level;

    private Boolean open;


    public ZTree() {
    }

    public ZTree(String id, String name, String code, String pId, int level, Boolean open) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.pId = pId;
        this.level = level;
        this.open = open;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Boolean isOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
