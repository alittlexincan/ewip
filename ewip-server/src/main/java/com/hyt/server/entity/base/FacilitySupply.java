package com.hyt.server.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Table(name = "base_facility_supply")
public class FacilitySupply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String  id;
    @Column(name = "district",length = 64)
    private String district;
    @Column(name = "placeName",length = 64)
    private String placeName;
    @Column(name = "code",length = 64)
    private String code;
    @Column(name = "lon" )
    private Double lon;
    @Column(name = "lat" )
    private Double lat;
    @Column(name = "tel",length = 64)
    private String tel;
    @Column(name = "name",length = 64)
    private String name;
    @Column(name = "amount",length = 64)
    private String amount;
    @Column(name = "materialUse",length = 64)
    private String materialUse;
    @Column(name = "address",length = 100)
    private String address;
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