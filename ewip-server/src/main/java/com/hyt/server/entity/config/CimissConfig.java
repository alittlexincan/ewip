package com.hyt.server.entity.config;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @ClassName CimissConfig
 * @Description Cimiss的配置信息
 * @Author Xincan
 * @Version 1.0
 **/
@Data
@ApiModel(value = "CimissConfig",description = "Cimiss的配置信息")
public class CimissConfig {
    /*
        UUID: id
        地区ID：areaId
        国家站号： stationId
        地区编码： stationCode
        cimiss ip地址：url
        接口用户名 : userId
        接口密码:  userPwd
        接口名称: interfaceId
        要素信息: elements
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String areaId;
    private String stationId;
    private String url;
    private String userId;
    private String userPwd;
    private String interfaceId;
    private String stationCode;
    private String elements;

    // from area table
    @Transient
    private String areaName;
    @Transient
    private String level;

}
