package com.zxyt.ocpp.client.entity.message;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "message_callback_child")
public class MessageCallbackChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "message_id",length = 64)
    private String messageId;

    @Column(name = "channel_code",length = 50)
    private String channelCode;

    @Column(name = "code",length = 50)
    private String code;

    @Column(name = "status",length = 1)
    private Integer status;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;


}
