package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 气象灾害影响路径和范围
 */
@Table(name = "base_disaster_route")
@Getter
@Setter
@Entity
public class DisasterRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "type",length = 64)
    private String type;

    @Column(name = "color",length = 64)
    private String color;

    @Column(name = "level",length = 64)
    private String level;

    @Column(name = "name",length = 64)
    private String name;

    @Column(name = "points",length = 100)
    private String points;

    @Column(name = "path",length = 100)
    private String path;

    @Column(name = "length",length = 64)
    private String length;

    @Column(name = "road_time")
    private Double roadTime;

    @Column(name = "ranges",length = 500)
    private String ranges;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "createUser", referencedColumnName = "id")
    private Employee createUser;

    @ManyToOne
    @JoinColumn(name = "updateUser", referencedColumnName = "id")
    private Employee updateUser;

}