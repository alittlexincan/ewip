package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 陡坡
 */
@Table(name = "base_risk_slope")
@Getter
@Setter
@Entity
public class RiskSlope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String  id;

    private String name;

    private Double lon;

    private Double lat;

    private String province;

    private String provinceCode;

    private String city;

    private String cityCode;

    private String district;

    private String districtCode;

    private String type;

    private String scale;

    private String stability;

    private String monitorOrgan;

    private String monitorPeople;

    private String contact;

    private String weatherCauses;

    private Double Prec24;

    private Double Prec1;

    private String areaName;

}