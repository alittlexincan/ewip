package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 应急避难所
 */
@Table(name = "base_facility_shelter")
@Getter
@Setter
@Entity
public class FacilityShelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String  id;

    @Column(name = "name",length = 64)
    private String name;

    @Column(name = "district",length = 64)
    private String district;

    @Column(name = "address",length = 100)
    private String address;

    @Column(name = "code",length = 64)
    private String code;

    /**
     * 占地面积（㎡）
     */
    @Column(name = "square" )
    private Double square;

    @Column(name = "capacity",length = 11)
    private Integer capacity;

    @Column(name = "unit",length = 64)
    private String unit;

    @Column(name = "principal",length = 64)
    private String principal;

    @Column(name = "tel",length = 64)
    private String tel;

    @Column(name = "phone",length = 64)
    private String phone;

    @Column(name = "lon" )
    private Double lon;

    @Column(name = "lat" )
    private Double lat;

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