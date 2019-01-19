package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 地质灾害隐患点
 */
@Table(name = "base_risk_geologic")
@Getter
@Setter
@Entity
public class RiskGeologic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String  id;

    private String name;

    private String district;

    private Double lon;

    private Double lat;

    private String fieldNumber;

    private String indoorNumber;

    private String type;

    private String street;

    private String rock;

    private String scale;

    private String stability;

    private String economicLoss;

    private Integer threadPeople;

    private Integer threadProperty;

    private String level;

    private String weatherCauses;

    private Double Prec24;

    private Double Prec1;

    private String areaName;

}