package com.hyt.server.entity.info;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "info_geo")
public class GeoInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;
    @Column(name = "geoCode",length = 30)
    private String geoCode;
    @Column(name = "geoName",length = 50)
    private String geoName;
    @Column(name = "geoType",length = 20)
    private String geoType;
    @Column(name = "lon")
    private Double lon;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "townCode",length = 100)
    private String townCode;

    @Column(name = "dmFactor",length = 2)
    private String dmFactor;

    @Column(name = "threatPerNum",length = 10)
    private Integer threatPerNum;

    @Column(name = "economicLoss",length = 10)
    private Integer economicLoss;

    @Column(name = "recordYear" ,length = 30)
    private String recordYear;

    @Column(name = "rainfallFactor",length = 1)
    private String rainfallFactor;
    @Column(name = "waterLineFactor",length = 1)
    private String waterLineFactor;
    @Column(name = "soilFactor",length = 1)
    private String soilFactor;
    @Column(name = "rainfallThreshold",length = 50)
    private String rainfallThreshold;
    @Column(name = "waterLineThreshold",length = 50)
    private String waterLineThreshold;
    @Column(name = "soilThreshold",length = 50)
    private String soilThreshold;

    @Column(name = "respUnit",length = 50)
    private String respUnit;

    @Column(name = "respPerson",length = 50)
    private String respPerson;

    @Column(name = "respMobilePhone",length = 50)
    private String respMobilePhone;

    @Column(name = "monitorPerson",length = 50)
    private String monitorPerson;

    @Column(name = "monMobilePhone",length = 50)
    private String monMobilePhone;

    @Column(name = "stationID",length = 200)
    private String stationID;
    @Column(name = "reportUnitCode",length = 50)
    private String reportUnitCode;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "createTime")
    private Date createTime;
}
