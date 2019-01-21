package com.zhxu.info.system.entity;

import com.zhxu.model.system.OrganizationType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "organization")
@Getter
@Setter
@Entity
public class Organization {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid")
    @Column(name = "id", length = 32)
    private String id;

    /**
     * 机构名称
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 机构编码
     */
    @Column(name = "code", length = 50)
    private String code;

    /**
     * 机构类型: 0:发布中心; 1:预案单位; 2:应急办
     */
    @Column(name = "type", length = 1)
    private OrganizationType type;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 所属区域
     */
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    /**
     * 机构下所有员工
     */
    @OneToMany(mappedBy = "organization")
    private Set<Employee> employees;
}
