package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 发布设施
 */
@Table(name = "base_facility_publish")
@Getter
@Setter
@Entity
public class FacilityPublish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String  id;

    @Column(name = "name",length = 64)
    private String name;

    @Column(name = "code",length = 64)
    private String code;

    @Column(name = "factory",length = 64)
    private String factory;

    @Column(name = "type",length = 64)
    private String type;

    @Column(name = "address",length = 100)
    private String address;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "unit",length = 64)
    private String unit;

    @Column(name = "equipmentUse",length = 100)
    private String equipmentUse;

    @Column(name = "principal",length = 64)
    private String principal;

    @Column(name = "phone",length = 64)
    private String phone;

    @Column(name = "status",length = 1)
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "createUser", referencedColumnName = "id")
    private Employee createUser;

    @ManyToOne
    @JoinColumn(name = "updateUser", referencedColumnName = "id")
    private Employee updateUser;

}