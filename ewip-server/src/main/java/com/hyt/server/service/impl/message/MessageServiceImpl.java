package com.hyt.server.service.impl.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.message.Message;
import com.hyt.server.entity.message.MessageContent;
import com.hyt.server.entity.message.MessageFile;
import com.hyt.server.entity.message.MessageUser;
import com.hyt.server.mapper.message.IMessageContentMapper;
import com.hyt.server.mapper.message.IMessageFileMapper;
import com.hyt.server.mapper.message.IMessageMapper;
import com.hyt.server.mapper.message.IMessageUserMapper;
import com.hyt.server.service.message.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * 一键发布业务实现层
 *
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("messageService")
public class MessageServiceImpl extends AbstractService<Message> implements IMessageService {

    @Autowired
    private IMessageMapper messageMapper;

    @Autowired
    private IMessageContentMapper messageContentMapper;

    @Autowired
    private IMessageUserMapper messageUserMapper;

    @Autowired
    private IMessageFileMapper messageFileMapper;

    /**
     * 添加一键发布相关信息
     * 1: 添加一键发布基础信息
     * 2：添加一键发布内容
     * 3：添加一件发布受众
     * 4：添加一键发布上传文件
     *
     * @param map
     * @return
     */
    @Override
    @Transactional
    public Message insert(Map<String, Object> map) {


        JSONObject json = new JSONObject(map);

        // 获取渠道（将字符串转换为JSONArray数组对象）同key值数据覆盖
        JSONArray channel =  JSONArray.parseArray(json.getString("channel"));
        json.put("channel", channel);

        // 获取地区（将字符串转换为JSONArray数组对象）同key值数据覆盖
        JSONArray area = JSONArray.parseArray(json.getString("area"));
        json.put("area",area);

        // 获取群组（将字符串转换为JSONArray数组对象）同key值数据覆盖
        JSONObject group = JSONObject.parseObject(json.getString("group"));
        json.put("group",group);

        System.out.println(json);

        // 1: 添加一键发布基础信息
        Message message = this.addMessage(json);
        String messageId = message.getId();
        // 2：添加一键发布内容
        this.addMessageContent(json, messageId);
        // 3：添加一件发布受众
        this.addMessageUser(json, messageId);
        // 4：添加一键发布上传文件
        this.addMessageFile(json, messageId);

        return message;
    }


    /**
     * 1：添加一键发布基本信息
     * @param json          接收信息
     * @return
     */
    private Message addMessage(JSONObject json){
        Message message = new Message();
        message.setTitle(json.getString("title"));
        message.setAreaId(json.getString("areaId"));
        message.setAreaName(json.getString("areaName"));
        message.setOrganizationId(json.getString("organizationId"));
        message.setOrganizationName(json.getString("organizationName"));
        message.setType(json.getInteger("type"));
        message.setTemplate(json.getInteger("template"));
        message.setSendTime(json.getTimestamp("sendTime"));
        this.messageMapper.insert(message);
        return  message;
    }

    /**
     * 2：添加一键发布内容信息
     * @param json          接收信息
     * @param messageId    基本信息ID
     * @return
     */
    private int addMessageContent(JSONObject json, String messageId){
        // 存储对象
        List<MessageContent> list = new ArrayList<>();
        // 获取渠道
        JSONArray channels =  json.getJSONArray("channel");
        // 获取地区
        JSONArray areas =  json.getJSONArray("area");

        // 循环获取渠道
        for(int i = 0; i<channels.size(); i++){
            JSONObject channel = channels.getJSONObject(i);
            String channelId = channel.getString("channelId");
            // 循环获取地区
            for(int j = 0; j<areas.size(); j++){
                JSONObject area = areas.getJSONObject(j);
                String areaId = area.getString("areaId");
                // 组装预警内容
                String content = json.getString("content_" + areaId);
                MessageContent messageContent = new MessageContent();
                messageContent.setMessageId(messageId);
                messageContent.setChannelId(channelId);
                messageContent.setAreaId(areaId);
                messageContent.setContent(content);
                list.add(messageContent);
            }
        }
        return this.messageContentMapper.insertList(list);
    }

    /**
     * 3：添加一键发布群组信息
     * @param json          接收信息
     * @param messageId    一键发布基本信息ID
     * @return
     */
    private int addMessageUser(JSONObject json, String messageId){
        // 存储对象
        List<MessageUser> list = new ArrayList<>();
        // 获取渠道
        JSONArray channels =  json.getJSONArray("channel");
        // 获取群组
        JSONObject group =  json.getJSONObject("group");
        // 循环获取渠道
        for(int i = 0; i<channels.size(); i++){
            JSONObject channel = channels.getJSONObject(i);
            JSONArray groupArray = group.getJSONArray(channel.getString("channelId"));
            for(int j = 0; j<groupArray.size(); j++){
                JSONObject userGroup = groupArray.getJSONObject(j);
                String userGroupName = userGroup.getString("userGroupName");
                userGroupName = userGroupName.substring(0,userGroupName.indexOf("("));
                MessageUser messageUser = new MessageUser();
                messageUser.setMessageId(messageId);
                messageUser.setChannelId(channel.getString("channelId"));
                messageUser.setUserGroupId(userGroup.getString("userGroupId"));
                messageUser.setUserGroupName(userGroupName);
                list.add(messageUser);
            }
        }
        return this.messageUserMapper.insertList(list);
    }


    /**
     * 4：添加一键发布上传文件信息
     * @param json
     * @param messageId
     * @return
     */
    private int addMessageFile(JSONObject json, String messageId){
        List<MessageFile> list = new ArrayList<>();
        JSONArray files = json.getJSONArray("files");
        if(files != null){
            for(int i = 0; i<files.size(); i++){
                JSONObject file = files.getJSONObject(i);
                MessageFile messageFile = new MessageFile();
                messageFile.setMessageId(messageId);
                messageFile.setName(file.getString("name"));
                messageFile.setSize(file.getString("size"));
                messageFile.setUrl(file.getString("url"));
                list.add(messageFile);
            }
            return this.messageFileMapper.insertList(list);
        }
        return 0;
    }
}
