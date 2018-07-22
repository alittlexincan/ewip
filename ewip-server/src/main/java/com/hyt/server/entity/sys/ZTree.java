package com.hyt.server.entity.sys;

import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Id;

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

    @Column(name = "area_id")
    private String areaId;

    private Boolean open;

    private int type;


    public ZTree() {
    }

    public ZTree(String id, String name, String code, String pId, int level, String areaId, Boolean open, int type) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.pId = pId;
        this.level = level;
        this.areaId = areaId;
        this.open = open;
        this.type = type;
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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
