package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 车站
 */
@Table(name = "base_unit_station")
@Getter
@Setter
@Entity
public class UnitStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    /**
     * 车站名称
     */
    @Column(name = "name",length = 64)
    private String name;

    /**
     * 车站名称
     */
    @Column(name = "district",length = 64)
    private String district;

    /**
     * 占地面积（㎡）
     */
    @Column(name = "square",length = 64)
    private Double square;

    /**
     * 车辆数
     */
    @Column(name = "vehicle",length = 64)
    private Integer vehicle;

    /**
     * 可容纳人数
     */
    @Column(name = "capacity",length = 64)
    private Integer capacity;

    /**
     * 经度
     */
    @Column(name = "lon" )
    private Double lon;

    /**
     * 纬度
     */
    @Column(name = "lat" )
    private Double lat;

    /**
     * 所属管辖单位
     */
    @Column(name = "unit",length = 64)
    private String unit;

    /**
     * 负责人
     */
    @Column(name = "principal",length = 64)
    private String principal;

    /**
     * 联系电话
     */
    @Column(name = "phone",length = 64)
    private String phone;

    /**
     * 车站描述
     */
    @Column(name = "description",length = 500)
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 所属地区
     */
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    /**
     * 创建人
     */
    @ManyToOne
    @JoinColumn(name = "createUser", referencedColumnName = "id")
    private Employee createUser;

    /**
     * 修改人
     */
    @ManyToOne
    @JoinColumn(name = "updateUser", referencedColumnName = "id")
    private Employee updateUser;

}