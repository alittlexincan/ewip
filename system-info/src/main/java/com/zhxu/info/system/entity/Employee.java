package com.zhxu.info.system.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "employee")
@Getter
@Setter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "login_name",length = 25, unique = true)
    private String loginName;

    @Column(name = "login_password",length = 64)
    private String loginPassword;

    @Column(name = "name",length = 25)
    private String name;

    @Column(name = "phone",length = 11)
    private String phone;

    @Column(name = "email",length = 50)
    private String email;

    @Column(name = "sex",length = 1)
    private Integer sex;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

}
