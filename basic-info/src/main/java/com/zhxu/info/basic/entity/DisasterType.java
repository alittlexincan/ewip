package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "base_disaster_type")
@Getter
@Setter
@Entity
public class DisasterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "type",length = 20)
    private String type;

    @Column(name = "severity",length = 20)
    private String severity;

    @Column(name = "color",length = 10)
    private String color;

    @Column(name = "level",length = 10)
    private String level;

    @Column(name = "damage",length = 10)
    private String damage;

    @Column(name = "code",length = 64)
    private String code;

    @Column(name = "name",length = 64)
    private String name;

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