package com.zhxu.message.model.wechat;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class WechatParam {

    // 发送时间
    private String sentTime;
    private String title;
    private String content;
    private String color;
    private String organizationName;

    // 灾种级别
    private int disasterColor;
    private String disasterName;
    // 模板id
    private String serviceTemplate;
    private String templateUrl;
    private int number;
    private String accessToken;
    private String template;
    private String msgType;
    // 预警地区
    private String area;
    // 微信关注的用户
    private List<String> userList;


}
