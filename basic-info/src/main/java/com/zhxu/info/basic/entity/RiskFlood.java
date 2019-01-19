package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 中小河流
 */
@Table(name = "base_risk_flood")
@Getter
@Setter
@Entity
public class RiskFlood {
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

    private String rangeLon;

    private String rangeLat;

    private String measures;

    private String monitorOrgan;

    private String monitorPeople;

    private String contact;

    private String weatherCauses;

    private Double Prec24;

    private Double Prec1;

    private String areaName;

}