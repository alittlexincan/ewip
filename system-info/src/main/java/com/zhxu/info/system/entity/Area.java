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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 地区名称
     */
    @Column(name = "area_name",length = 50)
    private String areaName;

    /**
     * 地区编码
     */
    @Column(name = "code",length = 12)
    private String code;

    /**
     * 地区级别(0：国家；1：省；2：市；3：县；4：乡镇，5：村)
     */
    @Column(name = "level",length = 1)
    private Integer level;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    private Double latitude;

    /**
     * 海拔高度
     */
    @Column(name = "altitude")
    private Double altitude;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 地区下所有机构
     */
    @OneToMany(mappedBy = "area")
    private Set<Organization> organizations;

    /**
     * 上级地区
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id", referencedColumnName = "id")
    private Area parent;

    /**
     * 下级地区
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Area> children;

}
