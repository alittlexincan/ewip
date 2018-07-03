package com.hyt.server.entity.sys;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Copyright (C), 2015-2018
 * FileName: User
 * Author:   JiangXincan
 * Date:     2018-4-30 10:44
 * Description: 用户实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "User",description = "用户信息")

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User{

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 36)
    private String id;

    @Getter
    @Setter
    @Column(name = "login_name",length = 25)
    private String loginName;

    @Getter
    @Setter
    @Column(name = "login_password",length = 64)
    private String loginPassword;

    @Getter
    @Setter
    @Column(name = "name",length = 25)
    private String name;

    @Getter
    @Setter
    @Column(name = "sex",length = 1)
    private Integer sex;

    @Getter
    @Setter
    @Column(name = "phone",length = 11)
    private String phone;

    @Getter
    @Setter
    @Column(name = "area_id",length = 36)
    private String areaId;


    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;


    public Date getCreateTime() {
        if(createTime == null){
            createTime = new Date();
        }
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
