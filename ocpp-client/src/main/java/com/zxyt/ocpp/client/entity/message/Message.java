package com.zxyt.ocpp.client.entity.message;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Message
 * @Description 一键发布消息实体类
 * @Author Xincan
 * @Version 1.0
 **/
@ApiModel(value = "Message",description = "一键发布信息")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "title",length = 100)
    private String title;

    @Column(name = "type",length = 2)
    private Integer type;

    @Column(name = "area_id",length = 64)
    private String areaId;

    @Column(name = "area_name",length = 100)
    private String areaName;

    @Column(name = "organization_id",length = 64)
    private String organizationId;

    @Column(name = "organization_name",length = 100)
    private String organizationName;

    @Column(name = "content",length = 4000)
    private String content;

    @Column(name = "record",length = 1)
    private Integer record;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

}
