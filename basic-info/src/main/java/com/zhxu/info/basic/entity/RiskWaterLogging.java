package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 内涝隐患点
 */
@Table(name = "base_risk_waterlogging")
@Getter
@Setter
@Entity
public class RiskWaterLogging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String  id;

    private String name;

    private String code;

    private Double lon;

    private Double lat;

    private Double alt;

    private String province;

    private String provinceCode;

    private String city;

    private String cityCode;

    private String district;

    private String districtCode;

    private String stree;

    private String streetCode;

    private Date startTime;

    private Date endTime;

    private Double depth;

    private Double area;

    private Double Prec1;

    private Double Prec2;

    private Double Prec3;

    private Double Prec6;

    private Double Prec12;

    private Double Prec24;

    private String rangeLon;

    private String rangeLat;

    private String monitorOrgan;

    private String monitorPeople;

    private String contact;

    private String areaName;

}