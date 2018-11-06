package com.hyt.server.entity.base;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@ApiModel(value = "RiskGeologic",description = "地质灾害隐患点")
@Table(name = "base_risk_geologic")
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getFieldNumber() {
        return fieldNumber;
    }

    public void setFieldNumber(String fieldNumber) {
        this.fieldNumber = fieldNumber == null ? null : fieldNumber.trim();
    }

    public String getIndoorNumber() {
        return indoorNumber;
    }

    public void setIndoorNumber(String indoorNumber) {
        this.indoorNumber = indoorNumber == null ? null : indoorNumber.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getRock() {
        return rock;
    }

    public void setRock(String rock) {
        this.rock = rock == null ? null : rock.trim();
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    public String getStability() {
        return stability;
    }

    public void setStability(String stability) {
        this.stability = stability == null ? null : stability.trim();
    }

    public String getEconomicLoss() {
        return economicLoss;
    }

    public void setEconomicLoss(String economicLoss) {
        this.economicLoss = economicLoss == null ? null : economicLoss.trim();
    }

    public Integer getThreadPeople() {
        return threadPeople;
    }

    public void setThreadPeople(Integer threadPeople) {
        this.threadPeople = threadPeople;
    }

    public Integer getThreadProperty() {
        return threadProperty;
    }

    public void setThreadProperty(Integer threadProperty) {
        this.threadProperty = threadProperty;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getWeatherCauses() {
        return weatherCauses;
    }

    public void setWeatherCauses(String weatherCauses) {
        this.weatherCauses = weatherCauses == null ? null : weatherCauses.trim();
    }


    public Double getPrec24() {
        return Prec24;
    }

    public void setPrec24(Double prec24) {
        Prec24 = prec24;
    }

    public Double getPrec1() {
        return Prec1;
    }

    public void setPrec1(Double prec1) {
        Prec1 = prec1;
    }
}