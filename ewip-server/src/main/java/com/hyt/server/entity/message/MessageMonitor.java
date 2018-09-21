package com.hyt.server.entity.message;

import io.swagger.annotations.ApiModel;

import javax.persistence.Id;

/**
 * Copyright (C), 2015-2018
 * FileName: Area
 * Author:   JiangXincan
 * Date:     2018-4-30 10:44
 * Description: 一键发布基础信息实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@ApiModel(value = "MessageMonitor",description = "一键发布信息监控实体类")
public class MessageMonitor{

    // 一键发布主表ID
    @Id
    private String messageId;

    // 一键发布类型
    private int type;

    // echarts 圆对应的值
    private int value;

    // echarts 圆对应的名称
    private String name;

    private String channelName;

    private String channelCode;

    private int total;

    private int success;

    private int fail;

    public MessageMonitor() {}

    public MessageMonitor(String messageId, int type, int value, String name, String channelName, String channelCode, int total, int success, int fail) {
        this.messageId = messageId;
        this.type = type;
        this.value = value;
        this.name = name;
        this.channelName = channelName;
        this.channelCode = channelCode;
        this.total = total;
        this.success = success;
        this.fail = fail;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }
}
