package com.zhxu.message.entity;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 渠道配置信息实体
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "channel_config")
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

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

}
