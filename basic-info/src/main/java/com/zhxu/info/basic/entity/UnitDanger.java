package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 危险品场所
 */
@Table(name = "base_unit_danger")
@Getter
@Setter
@Entity
public class UnitDanger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "district",length = 64)
    private String district;

    @Column(name = "name",length = 64)
    private String name;

    @Column(name = "project",length = 64)
    private String project;

    @Column(name = "building",length = 64)
    private String building;

    @Column(name = "number",length = 64)
    private Integer number;

    @Column(name = "address",length = 100)
    private String address;

    @Column(name = "lon" )
    private Double lon;

    @Column(name = "lat" )
    private Double lat;

    @Column(name = "product",length = 64)
    private String product;

    @Column(name = "tanks",length = 64)
    private String tanks;

    @Column(name = "machine",length = 1)
    private Integer machine;

    @Column(name = "lightning_people",length = 64)
    private String lightningPeople;

    @Column(name = "lightning_phone",length = 64)
    private String lightningPhone;

    /**
     * 占地面积（㎡）
     */
    @Column(name = "square",length = 64)
    private String square;

    @Column(name = "leader",length = 64)
    private String leader;

    @Column(name = "lightning_leader",length = 64)
    private String lightningLeader;

    @Column(name = "cadres",length = 64)
    private String cadres;

    @Column(name = "test_leader",length = 64)
    private String testLeader;

    @Column(name = "test_member",length = 64)
    private String testMember;

    private Date testDate;
    @Column(name = "report",length = 64)
    private String report;

    @Column(name = "status",length = 64)
    private String status;

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