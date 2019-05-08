package com.hyt.server.entity.info;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "info_pms")
public class PmsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;
    @Column(name = "schoolName",length = 50)
    private String schoolName;
    @Column(name = "schoolType",length = 50)
    private String schoolType;
    @Column(name = "lon")
    private Double lon;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "contact",length = 50)
    private String contact;
    @Column(name = "mobilePhone",length = 11)
    private String mobilePhone;
    @Column(name = "reportUnitCode",length = 50)
    private String reportUnitCode;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "createTime")
    private Date createTime;
}
