package com.hyt.server.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;
@ApiModel(value = "RiskWaterlogging",description = "内涝隐患点")
@Table(name = "base_risk_waterlogging")
public class RiskWaterlogging {
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

    @JSONField(format="yyyy-MM-dd")
    private Date startTime;

    @JSONField(format="yyyy-MM-dd")
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

    public RiskWaterlogging() {
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

    public Double getAlt() {
        return alt;
    }

    public void setAlt(Double alt) {
        this.alt = alt;
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

    public String getStree() {
        return stree;
    }

    public void setStree(String stree) {
        this.stree = stree == null ? null : stree.trim();
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode == null ? null : streetCode.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Double getPrec2() {
        return Prec2;
    }

    public void setPrec2(Double prec2) {
        Prec2 = prec2;
    }

    public Double getPrec3() {
        return Prec3;
    }

    public void setPrec3(Double prec3) {
        Prec3 = prec3;
    }

    public Double getPrec6() {
        return Prec6;
    }

    public void setPrec6(Double prec6) {
        Prec6 = prec6;
    }

    public Double getPrec12() {
        return Prec12;
    }

    public void setPrec12(Double prec12) {
        Prec12 = prec12;
    }

    public Double getPrec24() {
        return Prec24;
    }

    public void setPrec24(Double prec24) {
        Prec24 = prec24;
    }
}