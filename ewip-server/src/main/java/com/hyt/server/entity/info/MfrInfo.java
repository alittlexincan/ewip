package com.hyt.server.entity.info;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "info_mfr")
public class MfrInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;
    @Column(name = "mfrCode",length = 30)
    private String mfrCode;
    @Column(name = "mfrName",length = 50)
    private String mfrName;
    @Column(name = "lon")
    private Double lon;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "mfrHeight")
    private Double mfrHeight;

    @Column(name = "mfrAddrName",length = 50)
    private String mfrAddrName;

    @Column(name = "townCode",length = 100)
    private String townCode;

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

    @Column(name = "floodGrade")
    private Double floodGrade;
    @Column(name = "rainfallHour",length = 50)
    private String rainfallHour;
    @Column(name = "rainfall")
    private Double rainfall;
    @Column(name = "ensureWater")
    private Double ensureWater;
    @Column(name = "alertWater")
    private Double alertWater;
    @Column(name = "dikeHeight")
    private Double dikeHeight;

    @Column(name = "contact",length = 50)
    private String contact;
    @Column(name = "mobilePhone",length = 11)
    private String mobilePhone;
    @Column(name = "stationID",length = 200)
    private String stationID;
    @Column(name = "reportUnitCode",length = 50)
    private String reportUnitCode;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "createTime")
    private Date createTime;
}
