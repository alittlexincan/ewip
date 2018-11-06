package com.hyt.server.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "DisasterHistory",description = "历史灾情")
@Table(name = "base_disaster_history")
public class DisasterHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;


    private String type;

    private String severity;

    private String color;

    private String level;

    private String damage;

    private String code;

    private String name;

    @JSONField(format="yyyy-MM-dd")
    private Date startTime;

    @JSONField(format="yyyy-MM-dd")
    private Date endTime;

    private String scale;

    private String lon;

    private String lat;

    private String monitorPeople;

    @JSONField(format="yyyy-MM-dd")
    private Date monitorTime;

    private String monitorOrgan;

    private String contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity == null ? null : severity.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage == null ? null : damage.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon == null ? null : lon.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getMonitorPeople() {
        return monitorPeople;
    }

    public void setMonitorPeople(String monitorPeople) {
        this.monitorPeople = monitorPeople == null ? null : monitorPeople.trim();
    }

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public String getMonitorOrgan() {
        return monitorOrgan;
    }

    public void setMonitorOrgan(String monitorOrgan) {
        this.monitorOrgan = monitorOrgan == null ? null : monitorOrgan.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }
}