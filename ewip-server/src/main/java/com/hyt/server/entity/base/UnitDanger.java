package com.hyt.server.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;
@ApiModel(value = "UnitDanger",description = "危险品场所")
@Table(name = "base_unit_danger")
public class UnitDanger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    private String district;

    private String name;

    private String project;

    private String building;

    private Integer number;

    private String address;

    private Double lon;

    private Double lat;

    private String product;

    private String tanks;

    private Integer machine;

    private String lightningPeople;

    private String lightningPhone;

    private String area;

    private String leader;

    private String lightningLeader;

    private String cadres;

    private String testLeader;

    private String testMember;

    @JSONField(format="yyyy-MM-dd")
    private Date testDate;

    private String report;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building == null ? null : building.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getTanks() {
        return tanks;
    }

    public void setTanks(String tanks) {
        this.tanks = tanks == null ? null : tanks.trim();
    }

    public Integer getMachine() {
        return machine;
    }

    public void setMachine(Integer machine) {
        this.machine = machine;
    }

    public String getLightningPeople() {
        return lightningPeople;
    }

    public void setLightningPeople(String lightningPeople) {
        this.lightningPeople = lightningPeople == null ? null : lightningPeople.trim();
    }

    public String getLightningPhone() {
        return lightningPhone;
    }

    public void setLightningPhone(String lightningPhone) {
        this.lightningPhone = lightningPhone == null ? null : lightningPhone.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getLightningLeader() {
        return lightningLeader;
    }

    public void setLightningLeader(String lightningLeader) {
        this.lightningLeader = lightningLeader == null ? null : lightningLeader.trim();
    }

    public String getCadres() {
        return cadres;
    }

    public void setCadres(String cadres) {
        this.cadres = cadres == null ? null : cadres.trim();
    }

    public String getTestLeader() {
        return testLeader;
    }

    public void setTestLeader(String testLeader) {
        this.testLeader = testLeader == null ? null : testLeader.trim();
    }

    public String getTestMember() {
        return testMember;
    }

    public void setTestMember(String testMember) {
        this.testMember = testMember == null ? null : testMember.trim();
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report == null ? null : report.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}