package com.zhxu.info.system.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "permission")
@Getter
@Setter
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 64)
    private String id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "permission",length = 100)
    private String permission;

    @Column(name = "type", length = 20, columnDefinition="enum('menu','button')")
    private String type;

    @Column(name = "url", length = 100)
    private String url;

    @Column(name = "status", length = 1)
    private Integer status;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    private Set<Role> roles;
}
