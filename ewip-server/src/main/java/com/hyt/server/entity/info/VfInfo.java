package com.hyt.server.entity.info;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "info_vf")
public class VfInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;
    @Column(name = "areaCode",length = 30)
    private String areaCode;
    @Column(name = "vulnerFloodName",length = 50)
    private String vulnerFloodName;
    @Column(name = "lon")
    private Double lon;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "stationID",length = 200)
    private String stationID;
    @Column(name = "contact",length = 50)
    private String contact;
    @Column(name = "mobilePhone",length = 11)
    private String mobilePhone;
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
    @Column(name = "reportUnitCode",length = 50)
    private String reportUnitCode;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "createTime")
    private Date createTime;
}
