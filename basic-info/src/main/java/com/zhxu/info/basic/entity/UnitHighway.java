package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 高速公路
 */
@Table(name = "base_unit_highway")
@Getter
@Setter
@Entity
public class UnitHighway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "district",length = 64)
    private String district;

    @Column(name = "name",length = 64)
    private String name;

    @Column(name = "code",length = 64)
    private String code;

    @Column(name = "start",length = 64)
    private String start;

    @Column(name = "end",length = 64)
    private String end;

    @Column(name = "province",length = 64)
    private String province;

    @Column(name = "length",length = 64)
    private String length;

    @Column(name = "unit",length = 64)
    private String unit;

    @Column(name = "principal",length = 64)
    private String principal;

    @Column(name = "phone",length = 64)
    private String phone;

    @Column(name = "description",length = 500)
    private String description;

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