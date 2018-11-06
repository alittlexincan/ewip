package com.hyt.server.entity.base;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@ApiModel(value = "RiskWaterloggingArea",description = "易涝区")
@Table(name = "base_risk_waterlogging_area")
public class RiskWaterloggingArea {
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode == null ? null : streetCode.trim();
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures == null ? null : measures.trim();
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
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

    public Double getPrec1() {
        return Prec1;
    }

    public void setPrec1(Double prec1) {
        Prec1 = prec1;
    }
}