package com.hyt.server.entity.sys;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

@Table(name = "alarm_threshold")
public class AlarmThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "name",length = 50)
    private String name;

    @Column(name = "maxtmp",length = 12)
    private Integer maxtmp;

    @Column(name = "mintmp",length = 64)
    private Integer mintmp;

    @Column(name = "hour_rainnum",length = 1)
    private Integer hourRainNum;

    @Column(name = "day_rainnum")
    private Integer dayRainNum;

    @Column(name = "remarks")
    private String remarks;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    public AlarmThreshold() {
    }

    public AlarmThreshold(String name, Integer maxtmp, Integer mintmp, Integer hourRainNum, Integer dayRainNum, String remarks, Date createTime) {
        this.name = name;
        this.maxtmp = maxtmp;
        this.mintmp = mintmp;
        this.hourRainNum = hourRainNum;
        this.dayRainNum = dayRainNum;
        this.remarks = remarks;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxtmp() {
        return maxtmp;
    }

    public void setMaxtmp(Integer maxtmp) {
        this.maxtmp = maxtmp;
    }

    public Integer getMintmp() {
        return mintmp;
    }

    public void setMintmp(Integer mintmp) {
        this.mintmp = mintmp;
    }

    public Integer getHourRainNum() {
        return hourRainNum;
    }

    public void setHourRainNum(Integer hourRainNum) {
        this.hourRainNum = hourRainNum;
    }

    public Integer getDayRainNum() {
        return dayRainNum;
    }

    public void setDayRainNum(Integer dayRainNum) {
        this.dayRainNum = dayRainNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
