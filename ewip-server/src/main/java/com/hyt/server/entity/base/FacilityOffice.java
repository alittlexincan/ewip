package com.hyt.server.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "base_facility_office")
public class FacilityOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;
    @Column(name = "area_id",length = 64)
    private String areaId;
    @Column(name = "name",length = 64)
    private String name;
    @Column(name = "address",length = 225)
    private String address;
    @Column(name = "code",length = 225)
    private String code;
    @Column(name = "lon")
    private Double lon;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "tel",length = 64)
    private String tel;
    @Column(name = "principal",length = 64)
    private String principal;
    @Column(name = "phone",length = 64)
    private String phone;

    @Column(name = "createUser",length = 64)
    private String createUser;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "updateUser",length = 64)
    private String updateUser;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    private String areaName;
    private String createUserName;
    private String updateUserName;

}