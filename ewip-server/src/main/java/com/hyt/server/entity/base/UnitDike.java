package com.hyt.server.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "base_unit_dike")
public class UnitDike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;
    @Column(name = "district",length = 64)
    private String district;
    @Column(name = "name",length = 64)
    private String name;
    @Column(name = "river",length = 64)
    private String river;

    @Column(name = "startLon" )
    private Double startLon;
    @Column(name = "startLat" )
    private Double startLat;
    @Column(name = "endLon" )
    private Double endLon;
    @Column(name = "endLat" )
    private Double endLat;

    @Column(name = "flood_prevention",length = 64)
    private String floodPrevention;
    @Column(name = "length",length = 64)
    private String length;
    @Column(name = "altitude",length = 64)
    private String altitude;
    @Column(name = "distance",length = 64)
    private String distance;
    @Column(name = "height",length = 225)
    private String height;
    @Column(name = "width",length = 225)
    private String width;
    @Column(name = "soil",length = 225)
    private String soil;
    @Column(name = "slope_length",length = 225)
    private String slopeLength;
    @Column(name = "province",length = 64)
    private String province;
    @Column(name = "unit",length = 64)
    private String unit;
    @Column(name = "principal",length = 64)
    private String principal;
    @Column(name = "phone",length = 64)
    private String phone;
    @Column(name = "description",length = 500)
    private String description;

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