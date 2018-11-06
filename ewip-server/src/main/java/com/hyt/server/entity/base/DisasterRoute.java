package com.hyt.server.entity.base;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@ApiModel(value = "DisasterRoute",description = "气象灾害影响路径和范围")
@Table(name = "base_disaster_route")
public class DisasterRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    private String type;

    private String color;

    private String level;

    private String name;

    private String points;

    private String path;

    private String length;

    private Double roadTime;

    private String range;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points == null ? null : points.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length == null ? null : length.trim();
    }

    public Double getRoadTime() {
        return roadTime;
    }

    public void setRoadTime(Double roadTime) {
        this.roadTime = roadTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range == null ? null : range.trim();
    }
}