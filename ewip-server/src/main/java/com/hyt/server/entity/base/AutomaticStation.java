package com.hyt.server.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "UnitAutomaticStation",description = "区域自动站")
@Table(name = "base_automatic_station")
public class AutomaticStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;
    @Column(name = "area_id",length = 64)
    private String areaId;
    @Column(name = "name",length = 64)
    private String name;
    @Column(name = "code",length = 64)
    private String code;
    @Column(name = "address",length = 64)
    private String address;
    @Column(name = "isCheck",length = 64)
    private String isCheck;
    @Column(name = "lon",length = 64)
    private String lon;
    @Column(name = "lat",length = 64)
    private String lat;
    @Column(name = "height",length = 64)
    private String height;
    @Column(name = "pressureHeight",length = 64)
    private String pressureHeight;

    @Column(name = "rainfallStartMonth",length = 64)
    private String rainfallStartMonth;
    @Column(name = "rainfallEndMonth",length = 64)
    private String rainfallEndMonth;
    @Column(name = "type",length = 64)
    private String type;
    @Column(name = "factory",length = 64)
    private String factory;

    @Column(name = "element",length = 64)
    private String element;
    @Column(name = "airTemperature",length = 64)
    private String airTemperature;
    @Column(name = "temperature",length = 64)
    private String temperature;
    @Column(name = "pressure",length = 64)
    private String pressure;


    @Column(name = "rain",length = 64)
    private String rain;
    @Column(name = "windDirection",length = 64)
    private String windDirection;
    @Column(name = "windSpeed",length = 64)
    private String windSpeed;
    @Column(name = "humidity",length = 64)
    private String humidity;

    @Column(name = "landTemperature",length = 64)
    private String landTemperature;
    @Column(name = "deepTemperature",length = 64)
    private String deepTemperature;
    @Column(name = "solarRadiation",length = 64)
    private String solarRadiation;
    @Column(name = "visibility",length = 64)
    private String visibility;
    @Column(name = "supplyModel",length = 64)
    private String supplyModel;

    @JSONField( format="yyyy-MM-dd ")
    @Column(name = "installTime")
    private Date installTime;
    @JSONField(format="yyyy-MM-dd ")
    @Column(name = "enableTime")
    private Date enableTime;
    @Column(name = "environment",length = 64)
    private String environment;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPressureHeight() {
        return pressureHeight;
    }

    public void setPressureHeight(String pressureHeight) {
        this.pressureHeight = pressureHeight;
    }

    public String getRainfallStartMonth() {
        return rainfallStartMonth;
    }

    public void setRainfallStartMonth(String rainfallStartMonth) {
        this.rainfallStartMonth = rainfallStartMonth;
    }

    public String getRainfallEndMonth() {
        return rainfallEndMonth;
    }

    public void setRainfallEndMonth(String rainfallEndMonth) {
        this.rainfallEndMonth = rainfallEndMonth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(String airTemperature) {
        this.airTemperature = airTemperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getLandTemperature() {
        return landTemperature;
    }

    public void setLandTemperature(String landTemperature) {
        this.landTemperature = landTemperature;
    }

    public String getDeepTemperature() {
        return deepTemperature;
    }

    public void setDeepTemperature(String deepTemperature) {
        this.deepTemperature = deepTemperature;
    }

    public String getSolarRadiation() {
        return solarRadiation;
    }

    public void setSolarRadiation(String solarRadiation) {
        this.solarRadiation = solarRadiation;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getSupplyModel() {
        return supplyModel;
    }

    public void setSupplyModel(String supplyModel) {
        this.supplyModel = supplyModel;
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
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
}