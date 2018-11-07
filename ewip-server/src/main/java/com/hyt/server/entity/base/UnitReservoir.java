package com.hyt.server.entity.base;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@ApiModel(value = "UnitReservoir",description = "水库")
@Table(name = "base_unit_reservoir")
public class UnitReservoir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    private String name;

    private String district;

    private String address;

    private String districtCode;

    private Double lon;

    private Double lat;

    private String level;

    private Double storage;

    private Double limitStorage;

    private Double waterLimit;

    private Double waterNormal;

    private String principal;

    private String phone;

    private String waterLine;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Double getStorage() {
        return storage;
    }

    public void setStorage(Double storage) {
        this.storage = storage;
    }

    public Double getLimitStorage() {
        return limitStorage;
    }

    public void setLimitStorage(Double limitStorage) {
        this.limitStorage = limitStorage;
    }

    public Double getWaterLimit() {
        return waterLimit;
    }

    public void setWaterLimit(Double waterLimit) {
        this.waterLimit = waterLimit;
    }

    public Double getWaterNormal() {
        return waterNormal;
    }

    public void setWaterNormal(Double waterNormal) {
        this.waterNormal = waterNormal;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getWaterLine() {
        return waterLine;
    }

    public void setWaterLine(String waterLine) {
        this.waterLine = waterLine == null ? null : waterLine.trim();
    }
}