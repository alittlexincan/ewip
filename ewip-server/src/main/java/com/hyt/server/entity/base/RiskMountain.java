package com.hyt.server.entity.base;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@ApiModel(value = "RiskMountain",description = "山洪沟")
@Table(name = "base_risk_mountain")
public class RiskMountain {
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

    private String level;

    private String measures;

    private String monitorOrgan;

    private String monitorPeople;

    private String contact;

    private String weatherCauses;

    private Double Prec24;

    private Double Prec1;

    private String areaName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
    }

    public String getRangeLon() {
        return rangeLon;
    }

    public void setRangeLon(String rangeLon) {
        this.rangeLon = rangeLon == null ? null : rangeLon.trim();
    }

    public String getRangeLat() {
        return rangeLat;
    }

    public void setRangeLat(String rangeLat) {
        this.rangeLat = rangeLat == null ? null : rangeLat.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures == null ? null : measures.trim();
    }

    public String getMonitorOrgan() {
        return monitorOrgan;
    }

    public void setMonitorOrgan(String monitorOrgan) {
        this.monitorOrgan = monitorOrgan == null ? null : monitorOrgan.trim();
    }

    public String getMonitorPeople() {
        return monitorPeople;
    }

    public void setMonitorPeople(String monitorPeople) {
        this.monitorPeople = monitorPeople == null ? null : monitorPeople.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
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