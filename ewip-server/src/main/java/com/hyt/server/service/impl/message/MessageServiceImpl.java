package com.hyt.server.service.impl.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
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
import com.hyt.server.service.publish.INewPublishService;
import com.hyt.server.service.publish.IPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private IPublishService publishService;

    @Autowired
    INewPublishService newPublishService;

    @Override
    public PageInfo<Message> selectAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<Message> areaList = this.messageMapper.findAll(map);
        return new PageInfo<>(areaList);
    }


    /**
     * 根据ID查询一键发布信息
     *
     * @param map
     * @return
     */
    @Override
    @Transactional
    public JSONObject detail(Map<String, Object> map) {
        String messageId = map.get("messageId").toString();
        // 1：根据ID查询对应一键发布信息
        Message message = this.messageMapper.selectByMessageId(map);
        // 2：组装一键发布基本信息
        JSONObject result = (JSONObject) JSON.toJSON(message);
        // 3：组装一键发布内容,渠道，地区信息
        this.getMessageContentInfo(result, messageId);
        // 4：组装一键发布群组、受众
        this.getMessaggeUserInfo(result, messageId);
        // 5：组装一键发布文件信息
        this.getMessageFileInfo(result, messageId);
        // 6：统计用户组对应受众个数
        this.executeData(result);
        return result;
    }

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

        // 1: 添加一键发布基础信息
        Message message = this.addMessage(json);
        String messageId = message.getId();
        // 2：添加一键发布内容
        this.addMessageContent(json, messageId);
        // 3：添加一件发布受众
        this.addMessageUser(json, messageId);
        // 4：添加一键发布上传文件
        this.addMessageFile(json, messageId);

        // 组装一键发布内容,渠道，地区信息
        this.getMessageContentInfo(json, messageId);

        // 组装一键发布群组、受众
        this.getMessaggeUserInfo(json, messageId);

        json.put("id",messageId);

        /**
            Map<String, Object> param = json;
            System.out.println(json);
            // 调用分发平台
            this.publishService.publish(param);
        */

        newPublishService.publish(json);

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
                if(userGroupName.indexOf("(") > -1){
                    userGroupName = userGroupName.substring(0,userGroupName.indexOf("("));
                }
                MessageUser messageUser = new MessageUser();
                messageUser.setMessageId(messageId);
                messageUser.setChannelId(channel.getString("channelId"));
                messageUser.setUserGroupId(userGroup.getString("userGroupId"));
                messageUser.setUserGroupName(userGroupName);
                list.add(messageUser);
            }
        }
        if (list.size() > 0) {
            return this.messageUserMapper.insertList(list);
        } else {
            return 0;
        }
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


    /**
     * 根据id获取一键发布基本信息
     * @param map
     * @return
     */
    private JSONObject getMessageInfo(Map<String, Object> map){
        Message message = this.messageMapper.selectByMessageId(map);
        return (JSONObject) JSON.toJSON(message);
    }

    /**
     * 根据id获取预警内容信息
     * @param result
     * @param messageId
     */
    private void getMessageContentInfo (JSONObject result, String messageId){
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        System.out.println(result);
        List<MessageContent> list = this.messageContentMapper.selectByMessageId(map);
        if (list.size() > 0){

            List<JSONObject> channels = new ArrayList<>();
            List<JSONObject> areas = new ArrayList<>();

            for(MessageContent mc : list){
                // 组装发布渠道 去重
                JSONObject channel = new JSONObject();
                channel.put("channelId",mc.getChannelId());
                channel.put("channelName",mc.getChannelName());
                channel.put("channelCode",mc.getChannelCode());
                channels.add(channel);

                // 组装影响地区 去重
                JSONObject area = new JSONObject();
                area.put("areaId",mc.getAreaId());
                area.put("areaName",mc.getAreaName());
                area.put("areaCode",mc.getAreaCode());
                areas.add(area);
            }

            // 渠道
            List<String> channelId = new ArrayList<>();
            List<JSONObject> channelArray = channels.stream().filter(// 过滤去重
                    channel -> {
                        boolean flag = !channelId.contains(channel.getString("channelId"));
                        channelId.add(channel.getString("channelId"));
                        return flag;
                    }
            ).collect(Collectors.toList());

            // 地区
            List<String> areaId = new ArrayList<>();
            List<JSONObject> areaArray = areas.stream().filter(// 过滤去重
                    area -> {
                        boolean flag = !areaId.contains(area.getString("areaId"));
                        areaId.add(area.getString("areaId"));
                        return flag;
                    }
            ).collect(Collectors.toList());

            // 预警内容
            // Map<String, List<MessageContent>> content = list.stream().collect(Collectors.groupingBy(MessageContent::getChannelId));

            Map<String, String> content = new HashMap<>();

            list.forEach(message -> {
                content.put(message.getAreaId(), message.getContent());
            });

            result.put("channels",channelArray);
            result.put("areas",areaArray);
            result.put("contents",content);
        }
    }


    /**
     * 根据id获取预警发布对象信息
     * @param result
     * @param messageId
     * @return
     */
    private void getMessaggeUserInfo(JSONObject result, String messageId){
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        List<MessageUser> list = this.messageUserMapper.selectByMessageId(map);
        // 组装渠道下的群组，一个渠道可能对应多个群组
        JSONObject group = new JSONObject();
        // 组装渠道下的用户，一个群组可能对应多个用户
        JSONObject user = new JSONObject();
        if(list.size() > 0){
            // 渠道去重
            Map<String, List<MessageUser>> groupList = list.stream().collect(Collectors.groupingBy(MessageUser::getChannelId));
            list.forEach(ms -> {
                // 群组过滤不必要字段
                JSONArray groupArray = new JSONArray();
                // 渠道对应的群组去重
                groupList.get(ms.getChannelId()).stream().collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getChannelId() + ";" + o.getUserGroupId()))),
                        ArrayList::new
                )).forEach( gr -> {
                    JSONObject g = new JSONObject();
                    g.put("userGroupId", gr.getUserGroupId());
                    g.put("userGroupName", gr.getUserGroupName());
                    groupArray.add(g);
                });
                // 用户过滤不必要字段
                JSONArray userGroupArray = new JSONArray();
                // 组装群组下的受众
                list.stream()
                        .filter(p -> p.getUserGroupId().equals(ms.getUserGroupId()))
                        .collect(Collectors.toList())
                        .forEach(ug -> {
                            JSONObject g = new JSONObject();
                            g.put("userName", ug.getUserName());
                            g.put("userCode", ug.getUserCode());
                            g.put("channelCode", ug.getChannelCode());
                            g.put("longitude", ug.getLongitude());
                            g.put("latitude", ug.getLatitude());
                            g.put("altitude", ug.getAltitude());
                            userGroupArray.add(g);
                        });
                // 在当前渠道下追加群组
                group.put(ms.getChannelId(), groupArray);
                // 当前群组下追加受众用户
                user.put(ms.getUserGroupId(), userGroupArray);
            });
        }
        result.put("users", user);
        result.put("groups", group);
    }

    /**
     * 根据id获取一键发布上传文件信息
     * @param messageId
     * @return
     */
    private void getMessageFileInfo(JSONObject result, String messageId){
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        List<MessageFile> list = this.messageFileMapper.selectByMessageId(map);
        if(list.size() > 0){
            JSONArray fileArray = new JSONArray();
            list.forEach(file -> {
                JSONObject json = new JSONObject();
                json.put("name", file.getName());
                json.put("size", file.getSize());
                json.put("url", file.getUrl());
                fileArray.add(json);
            });
            result.put("files",fileArray);
        }else {
            result.put("files", new JSONArray());
        }
    }

    /**
     * 数据处理
     * @param json
     */
    private void executeData(JSONObject json){
        JSONArray channels = json.getJSONArray("channel");
        JSONObject group = json.getJSONObject("group");
        JSONObject user = json.getJSONObject("user");
        channels.forEach( channel -> {
            JSONObject chn = (JSONObject) channel;
            String channelId = chn.getString("channelId");
            JSONArray groups = group.getJSONArray(channelId);
            if(groups!=null){
                groups.forEach(gro -> {
                    JSONObject g = (JSONObject) gro;
                    JSONArray arr = user.getJSONArray(g.getString("userGroupId"));
                    int num = 0;
                    for (int i = 0; i<arr.size(); i++){
                       JSONObject us = arr.getJSONObject(i);
                        if(us.getString("userName")!=null) num++;
                    }
                    g.put("userGroupName", g.getString("userGroupName") + (num == 0 ? "" : "(" + num + ")"));

                });
            }
        });

    }

}
