package com.zhxu.info.system.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "permission")
@Getter
@Setter
@Entity
public class Permission {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid")
    @Column(name = "id", length = 32)
    private String id;

    /**
     * 权限名称
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:detail
     */
    @Column(name = "permission",length = 100)
    private String permission;

    /**
     * 资源类型，[menu、button]
     */
    @Column(name = "type", length = 20, columnDefinition="enum('menu','button')")
    private String type;

    /**
     * 权限描述
     */
    @Column(name = "description", length = 200)
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}
