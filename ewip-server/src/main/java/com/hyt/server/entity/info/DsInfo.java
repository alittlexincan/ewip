package com.hyt.server.entity.info;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "info_ds")
public class DsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;
    @Column(name = "clientID",length = 64)
    private String clientID;
    @Column(name = "factoryID",length = 10)
    private String factoryID;
    @Column(name = "factoryServerID",length = 12)
    private String factoryServerID;
    @Column(name = "originalClientID",length = 50)
    private String originalClientID;
    @Column(name = "clientStyle",length = 10)
    private String clientStyle;
    @Column(name = "lon")
    private Double lon;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "address",length = 200)
    private String address;
    @Column(name = "clientPerson",length = 60)
    private String clientPerson;
    @Column(name = "clientTel",length = 12)
    private String clientTel;

    @Column(name = "reportUnitCode",length = 50)
    private String reportUnitCode;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "createTime")
    private Date createTime;

    private String clientStatus;
    private String updateTime;
}
