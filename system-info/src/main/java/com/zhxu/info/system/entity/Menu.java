package com.zhxu.info.system.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "menu")
@Getter
@Setter
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "name",length = 50)
    private String name;

    @Column(name = "code",length = 50)
    private String code;

    @Column(name = "url",length = 50)
    private String url;

    @Column(name = "icon",length = 50)
    private String icon;

    @Column(name = "level", length = 1)
    private Integer level;

    @Column(name = "turn", length = 1)
    private Integer turn;

    @Column(name = "area_id", length = 64)
    private String areaId;

    @Column(name = "organization_id", length = 64)
    private String organizationId;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private Set<Role> roles;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id", referencedColumnName = "id")
    private Menu parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Menu> children;

}
