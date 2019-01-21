package com.zhxu.info.system.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "employee")
@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid")
    @Column(name = "id", length = 32)
    private String id;

    /**
     * 登录名称
     */
    @Column(name = "login_name",length = 25, unique = true)
    private String loginName;

    /**
     * 登录密码
     */
    @Column(name = "login_password",length = 64)
    private String loginPassword;

    /**
     * 用户真实名称
     */
    @Column(name = "name",length = 25)
    private String name;

    /**
     * 用户电话号码
     */
    @Column(name = "phone",length = 11)
    private String phone;

    /**
     * 用户电话号码
     */
    @Column(name = "email",length = 50)
    private String email;

    /**
     * 用户性别
     */
    @Column(name = "sex",length = 1)
    private Integer sex;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 所属机构
     */
    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;

    /**
     * 用户角色
     */
    @ManyToMany
    @JoinTable(
            name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

}
