package com.zhxu.info.system.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "job")
@Getter
@Setter
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 相关用户
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 所属职务
     */
    @Column(name = "job", length = 200)
    private String job;

    /**
     * 所属职责
     */
    @Column(name = "duty", length = 200)
    private String duty;

    /**
     * 分管领导电话
     */
    @Column(name = "leader_mobile", length = 200)
    private String leaderMobile;
}
