package com.zhxu.message.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "channel_config")
@Getter
@Setter
@Entity
public class ChannelConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "area_id",length = 64)
    private String areaId;

    @Column(name = "organization_id",length = 64)
    private String organizationId;

    @Column(name = "channel_code",length = 50)
    private String channelType;

    @Column(name = "content",length = 4000)
    private String content;

    @Column(name = "create_time")
    private Date createTime;

}
