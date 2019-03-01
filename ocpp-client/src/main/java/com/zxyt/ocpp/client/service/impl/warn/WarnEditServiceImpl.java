package com.zxyt.ocpp.client.service.impl.warn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxyt.ocpp.client.config.common.universal.AbstractService;
import com.zxyt.ocpp.client.entity.message.MessageUser;
import com.zxyt.ocpp.client.entity.warn.WarnEdit;
import com.zxyt.ocpp.client.entity.warn.WarnEditContent;
import com.zxyt.ocpp.client.entity.warn.WarnEditFile;
import com.zxyt.ocpp.client.entity.warn.WarnEditUser;
import com.zxyt.ocpp.client.mapper.warn.IWarnEditContentMapper;
import com.zxyt.ocpp.client.mapper.warn.IWarnEditFileMapper;
import com.zxyt.ocpp.client.mapper.warn.IWarnEditMapper;
import com.zxyt.ocpp.client.mapper.warn.IWarnEditUserMapper;
import com.zxyt.ocpp.client.service.warn.IWarnEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service("warnEditService")
public class WarnEditServiceImpl extends AbstractService<WarnEdit> implements IWarnEditService {

    @Autowired
    private IWarnEditMapper warnEditMapper;

    @Autowired
    private IWarnEditContentMapper warnEditContentMapper;

    @Autowired
    private IWarnEditUserMapper warnEditUserMapper;

    @Autowired
    private IWarnEditFileMapper warnEditFileMapper;


    @Override
    public JSONObject insert(JSONObject json) {

        // 获取渠道（将字符串转换为JSONArray数组对象）同key值数据覆盖
        JSONArray channels =  JSONArray.parseArray(json.getString("channel"));
        json.put("channels", channels);

        // 获取地区（将字符串转换为JSONArray数组对象）同key值数据覆盖
        JSONArray areas = JSONArray.parseArray(json.getString("area"));
        json.put("areas",areas);

        // 获取群组（将字符串转换为JSONArray数组对象）同key值数据覆盖
        JSONObject groups = JSONObject.parseObject(json.getString("group"));
        json.put("groups",groups);

        WarnEdit warnEdit = addWarnEdit(json);  // 1：添加预警编辑基本信息
        String warnEditId = warnEdit.getId();   // 2：预警基础信息ID
        if(!StringUtils.isEmpty(warnEditId)){
            json.put("id",warnEditId);
            addWarnEditContent(json);   // 3：添加预警编辑内容信息
            addWarnEditUser(json);      // 4：添加预警编辑群组信息
            addWarnEditFile(json);      // 6：添加预警编辑上传文件信息

            getWarnUserInfo(json, warnEditId);

            json.put("code", 200);
            json.put("msg","一键发布成功");
            return json;
        }
        json.put("code", 500);
        json.put("msg","一键发布失败");
        return json;
    }

    private WarnEdit addWarnEdit(JSONObject json){
        WarnEdit warnEdit = new WarnEdit();
        warnEdit.setTitle(json.getString("title"));
        warnEdit.setAreaId(json.getString("areaId"));
        warnEdit.setOrganizationId(json.getString("organizationId"));
        warnEdit.setDisasterId(json.getString("disasterId"));
        warnEdit.setDisasterName(json.getString("disasterName"));
        warnEdit.setDisasterColor(json.getInteger("disasterColor"));
        warnEdit.setDisasterLevel(json.getInteger("disasterLevel"));
        warnEdit.setWarnType(json.getString("warnType"));
        warnEdit.setMsgType(json.getString("msgType"));
        warnEdit.setScope(json.getString("scope"));
        warnEdit.setEditTime(json.getTimestamp("editTime"));
        warnEdit.setForecastTime(json.getTimestamp("forecastTime"));
        warnEdit.setInvalidTime(json.getTimestamp("invalidTime"));
        warnEdit.setRecord(json.getInteger("record"));
        warnEdit.setMeasure(json.getString("measure"));
        warnEdit.setInstruction(json.getString("instruction"));
        warnEdit.setFlow(json.getString("flow"));
        warnEdit.setStatus(json.getInteger("status")); // 预警编辑时此状态为0，预警真正发布之后修改此状态为1 （0：未发布；1：以发布；2：解除）
        this.warnEditMapper.insertSelective(warnEdit);
        return  warnEdit;
    }

    /**
     * 2：添加预警编辑内容信息
     * @param json          接收信息
     * @return
     */
    private int addWarnEditContent(JSONObject json){
        // 存储对象
        List<WarnEditContent> list = new ArrayList<>();
        // 获取渠道
        JSONArray channels =  json.getJSONArray("channels");
        // 获取地区
        JSONArray areas =  json.getJSONArray("areas");
        // 循环获取渠道
        for(int i = 0; i<channels.size(); i++){
            JSONObject channel = channels.getJSONObject(i);
            String channelId = channel.getString("channelId");
            String content=json.getString("content_"+channelId);
            // 循环获取地区
            for(int j = 0; j<areas.size(); j++){
                JSONObject area = areas.getJSONObject(j);
                String areaId = area.getString("areaId");
                // 组装预警内容

                WarnEditContent warnEditContent = new WarnEditContent();
                warnEditContent.setWarnEditId(json.getString("id"));
                warnEditContent.setChannelId(channelId);
                warnEditContent.setAreaId(areaId);
                warnEditContent.setContent(content);
                list.add(warnEditContent);
            }
        }
        return this.warnEditContentMapper.insertList(list);
    }

    /**
     * 3：添加预警编辑群组信息
     * @param json          接收信息
     * @return
     */
    private int addWarnEditUser(JSONObject json){
        // 存储对象
        List<WarnEditUser> list = new ArrayList<>();
        // 获取渠道
        JSONArray channels =  json.getJSONArray("channels");
        // 获取群组
        JSONObject group =  json.getJSONObject("groups");
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
                WarnEditUser warnEditUser = new WarnEditUser();
                warnEditUser.setWarnEditId(json.getString("id"));
                warnEditUser.setChannelId(channel.getString("channelId"));
                warnEditUser.setUserGroupId(userGroup.getString("userGroupId"));
                warnEditUser.setUserGroupName(userGroupName);
                list.add(warnEditUser);
            }
        }
        return this.warnEditUserMapper.insertList(list);
    }

    /**
     * 5：添加预警编辑上传文件信息
     * @param json
     * @return
     */
    private int addWarnEditFile(JSONObject json){
        List<WarnEditFile> list = new ArrayList<>();
        JSONArray files = json.getJSONArray("files");
        if(files != null && files.size() > 0 ){
            for(int i = 0; i<files.size(); i++){
                JSONObject file = files.getJSONObject(i);
                WarnEditFile warnEditFile = new WarnEditFile();
                warnEditFile.setWarnEditId(json.getString("id"));
                warnEditFile.setName(file.getString("name"));
                warnEditFile.setSize(file.getString("size"));
                warnEditFile.setUrl(file.getString("url"));
                list.add(warnEditFile);
            }
            return this.warnEditFileMapper.insertList(list);
        }
        return 0;
    }

    private void getWarnUserInfo(JSONObject result, String warnEditId){
        Map<String, Object> map = new HashMap<>();
        map.put("warnEditId", warnEditId);
        List<WarnEditUser> list = this.warnEditUserMapper.selectByWarnEditId(map);
        if(list.size() > 0){
            // 组装渠道下的群组，一个渠道可能对应多个群组
            JSONObject group = new JSONObject();
            // 组装渠道下的用户，一个群组可能对应多个用户
            JSONObject user = new JSONObject();
            // 渠道去重
            Map<String, List<WarnEditUser>> groupList = list.stream().collect(Collectors.groupingBy(WarnEditUser::getChannelId));
            list.forEach(weu -> {
                // 群组过滤不必要字段
                JSONArray groupArray = new JSONArray();
                // 渠道对应的群组去重
                groupList.get(weu.getChannelId()).stream().collect(Collectors.collectingAndThen(
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
                        .filter(p -> p.getUserCode() != null && p.getUserGroupId().equals(weu.getUserGroupId()))
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
                group.put(weu.getChannelId(), groupArray);
                // 当前群组下追加受众用户
                user.put(weu.getUserGroupId(), userGroupArray);
            });
            result.put("users", user);
            result.put("groups", group);
        }
    }


}
