package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 森林火险
 */
@Table(name = "base_risk_forest")
@Getter
@Setter
@Entity
public class RiskForest {
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

    private Double area;

    private String measures;

    private String monitorOrgan;

    private String monitorPeople;

    private String contact;

    private String weatherCauses;

    private Double tmax;

    private String areaName;

}