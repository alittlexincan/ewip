package com.zhxu.info.system.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "area")
@Getter
@Setter
@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "area_name",length = 50)
    private String areaName;

    @Column(name = "code",length = 12)
    private String code;

    @Column(name = "level",length = 1)
    private Integer level;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "altitude")
    private Double altitude;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Organization> organizations;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id", referencedColumnName = "id")
    private Area parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Area> children;

}
