package com.hyt.server.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "base_unit_agricultur_park")
public class UnitAgriculturPark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "district",length = 64)
    private String district;
    @Column(name = "name",length = 64)
    private String name;
    @Column(name = "address",length = 100)
    private String address;
    @Column(name = "lon" )
    private Double lon;
    @Column(name = "lat" )
    private Double lat;

    @Column(name = "level",length = 64)
    private String level;
    @Column(name = "type",length = 64)
    private String type;
    @Column(name = "crops",length = 11)
    private Integer crops;
    @Column(name = "area",length = 64)
    private String area;

    @Column(name = "unit",length = 64)
    private String unit;
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