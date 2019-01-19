package com.zhxu.info.basic.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 区域自动站
 */
@Table(name = "base_automatic_station")
@Getter
@Setter
@Entity
public class AutomaticStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "name",length = 64)
    private String name;

    @Column(name = "code",length = 64)
    private String code;

    @Column(name = "address",length = 64)
    private String address;

    @Column(name = "isCheck",length = 64)
    private String isCheck;

    @Column(name = "lon",length = 64)
    private String lon;

    @Column(name = "lat",length = 64)
    private String lat;

    @Column(name = "height",length = 64)
    private String height;

    @Column(name = "pressureHeight",length = 64)
    private String pressureHeight;

    @Column(name = "rainfallStartMonth",length = 64)
    private String rainfallStartMonth;

    @Column(name = "rainfallEndMonth",length = 64)
    private String rainfallEndMonth;

    @Column(name = "type",length = 64)
    private String type;

    @Column(name = "factory",length = 64)
    private String factory;

    @Column(name = "element",length = 64)
    private String element;

    @Column(name = "airTemperature",length = 64)
    private String airTemperature;

    @Column(name = "temperature",length = 64)
    private String temperature;

    @Column(name = "pressure",length = 64)
    private String pressure;

    @Column(name = "rain",length = 64)
    private String rain;

    @Column(name = "windDirection",length = 64)
    private String windDirection;

    @Column(name = "windSpeed",length = 64)
    private String windSpeed;

    @Column(name = "humidity",length = 64)
    private String humidity;

    @Column(name = "landTemperature",length = 64)
    private String landTemperature;

    @Column(name = "deepTemperature",length = 64)
    private String deepTemperature;

    @Column(name = "solarRadiation",length = 64)
    private String solarRadiation;

    @Column(name = "visibility",length = 64)
    private String visibility;

    @Column(name = "supplyModel",length = 64)
    private String supplyModel;

    @Column(name = "installTime")
    private Date installTime;

    @Column(name = "enableTime")
    private Date enableTime;

    @Column(name = "environment",length = 64)
    private String environment;

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