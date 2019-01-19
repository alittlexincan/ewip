package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 易涝区
 */
@Table(name = "base_risk_waterlogging_area")
@Getter
@Setter
@Entity
public class RiskWaterLoggingArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String  id;

    private String name;

    private String code;

    private Double lon;

    private Double lat;

    private String province;

    private String provinceCode;

    private String city;

    private String cityCode;

    private String district;

    private String districtCode;

    private String street;

    private String streetCode;

    private String measures;

    private Double depth;

    private Double area;

    private Double Prec1;

    private String monitorOrgan;

    private String monitorPeople;

    private String contact;

    private String areaName;

}