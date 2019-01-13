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
    @Column(name = "district",length = 64)
    private String district;
    @Column(name = "name",length = 64)
    private String name;
    @Column(name = "project",length = 64)
    private String project;
    @Column(name = "building",length = 64)
    private String building;
    @Column(name = "number",length = 64)
    private Integer number;
    @Column(name = "address",length = 100)
    private String address;
    @Column(name = "lon" )
    private Double lon;
    @Column(name = "lat" )
    private Double lat;
    @Column(name = "product",length = 64)
    private String product;
    @Column(name = "tanks",length = 64)
    private String tanks;
    @Column(name = "machine",length = 1)
    private Integer machine;
    @Column(name = "lightning_people",length = 64)
    private String lightningPeople;
    @Column(name = "lightning_phone",length = 64)
    private String lightningPhone;
    @Column(name = "area",length = 64)
    private String area;
    @Column(name = "leader",length = 64)
    private String leader;
    @Column(name = "lightning_leader",length = 64)
    private String lightningLeader;
    @Column(name = "cadres",length = 64)
    private String cadres;
    @Column(name = "test_leader",length = 64)
    private String testLeader;
    @Column(name = "test_member",length = 64)
    private String testMember;

    @JSONField(format="yyyy-MM-dd")
    private Date testDate;
    @Column(name = "report",length = 64)
    private String report;
    @Column(name = "status",length = 64)
    private String status;

    @Column(name = "createUser",length = 64)
    private String createUser;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "updateUser",length = 64)
    private String updateUser;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    private String areaName;
    private String createUserName;
    private String updateUserName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

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