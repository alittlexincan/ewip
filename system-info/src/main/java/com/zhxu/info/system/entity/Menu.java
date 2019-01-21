package com.zhxu.info.system.entity;

import com.zhxu.model.system.OrganizationType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "menu")
@Getter
@Setter
@Entity
public class Menu {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid")
    @Column(name = "id", length = 32)
    private String id;

    /**
     * 菜单名称
     */
    @Column(name = "name",length = 50)
    private String name;

    /**
     * 菜单路径
     */
    @Column(name = "url",length = 50)
    private String url;

    /**
     * 菜单图标
     */
    @Column(name = "icon",length = 50)
    private String icon;

    /**
     * 菜单管理：1：一级；2：二级；3：三级
     */
    @Column(name = "level", length = 1)
    private Integer level;

    /**
     * 菜单排序
     */
    @Column(name = "`order`", length = 1)
    private Integer order;

    /**
     * 所属机构类型
     */
    @Column(name = "organization_type", length = 64)
    private OrganizationType organizationType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 上级菜单
     */
    @ManyToOne
    @JoinColumn(name = "p_id", referencedColumnName = "id")
    private Menu parent;

    /**
     * 下级菜单
     */
    @OneToMany(mappedBy = "parent")
    private Set<Menu> children;

}
