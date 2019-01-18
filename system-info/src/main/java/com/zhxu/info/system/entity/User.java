package com.zhxu.info.system.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user")
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 用户名称
     */
    @Column(name = "name",length = 50)
    private String name;

    /**
     * 手机号码
     */
    @Column(name = "mobile", length = 200)
    private String mobile;

    /**
     * 邮箱地址
     */
    @Column(name = "email", length = 200)
    private String email;

    /**
     * 所属地区
     */
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    /**
     * 所属职务
     */
    @OneToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 年龄
     */
    @Column(name = "age")
    private Integer age;

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
     * 详细地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}
