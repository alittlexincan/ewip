package com.hyt.server.entity.message;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.Id;
import java.util.Date;

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

    // 终端编码
    private String code;

    // 接收结果
    private int status;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public MessageMonitor() {}

    public MessageMonitor(String messageId, int type, int value, String name, String channelName, String channelCode, int total, int success, int fail, String code, int status, Date createTime) {
        this.messageId = messageId;
        this.type = type;
        this.value = value;
        this.name = name;
        this.channelName = channelName;
        this.channelCode = channelCode;
        this.total = total;
        this.success = success;
        this.fail = fail;
        this.code = code;
        this.status = status;
        this.createTime = createTime;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
