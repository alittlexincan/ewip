package com.zhxu.info.system.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "role")
@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 64)
    private String id;

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "status", length = 1)
    private Integer status;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
    )
    private Set<Permission> permissions;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_menu",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id")
    )
    private Set<Menu> menus;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Employee> employees;

}
