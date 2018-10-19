package com.hyt.server.service.impl.warn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.message.Message;
import com.hyt.server.entity.sys.Organization;
import com.hyt.server.entity.sys.Warn;
import com.hyt.server.entity.warn.*;
import com.hyt.server.mapper.sys.IOrganizationMapper;
import com.hyt.server.mapper.warn.*;
import com.hyt.server.service.warn.IWarnEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: XincanJiang
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("warnEditService")
public class WarnEditServiceImpl extends AbstractService<WarnEdit> implements IWarnEditService {

    /**
     * 预警编辑 基本信息 数据接口层
     */
    @Autowired
    private IWarnEditMapper warnEditMapper;

    /**
     * 预警编辑 内容信息 数据接口层
     */
    @Autowired
    private IWarnEditContentMapper warnEditContentMapper;


    /**
     * 预警编辑 流程信息 数据接口层
     */
    @Autowired
    private IWarnEditFlowMapper warnEditFlowMapper;

    /**
     * 预警编辑 受众信息 数据接口层
     */
    @Autowired
    private IWarnEditUserMapper warnEditUserMapper;

    /**
     * 预警编辑 文件上传 数据接口层
     */
    @Autowired
    private IWarnEditFileMapper warnEditFileMapper;

    /**
     * 预警编辑 机构信息 数据接口层
     */
    @Autowired
    private IOrganizationMapper organizationMapper;

    @Override
    public PageInfo<WarnEdit> findAll(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<WarnEdit> list = this.warnEditMapper.findAll(map);
        return new PageInfo<>(list);
    }

    /**
     * 添加预警相关信息
     *  1：添加预警编辑基本信息
     *  2：添加预警编辑内容信息
     *  3：添加预警编辑群组信息
     *  4：添加预警编辑流程信息
     *  5：添加预警编辑上传文件信息
     * @param map
     * @return
     */
    @Override
    @Transactional
    public WarnEdit insert(Map<String, Object> map) {
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

        // 1：添加预警编辑基本信息
        WarnEdit warnEdit = addWarnEdit(json);

        // 预警基础信息ID
        String warnEditId = warnEdit.getId();

        // 2：添加预警编辑内容信息
        addWarnEditContent(json, warnEditId);

        // 3：添加预警编辑群组信息
        addWarnEditUser(json, warnEditId);

        // 4：添加预警编辑流程信息
        addWarnEditFlow(json, warnEditId);

        // 5：添加预警编辑上传文件信息
        addWarnEditFile(json, warnEditId);

        return warnEdit;
    }


    /**
     * 1：添加预警编辑基本信息
     * @param json          接收信息
     * @return
     */
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
        warnEdit.setCurrentFlow(json.getInteger("currentFlow"));
        warnEdit.setNextFlow(json.getInteger("nextFlow"));
        warnEdit.setStatus(json.getInteger("status")); // 预警编辑时此状态为0，预警真正发布之后修改此状态为1 （0：未发布；1：以发布；2：解除）
        this.warnEditMapper.insertSelective(warnEdit);
        return  warnEdit;
    }

    /**
     * 2：添加预警编辑内容信息
     * @param json          接收信息
     * @param warnEditId    预警基本信息ID
     * @return
     */
    private int addWarnEditContent(JSONObject json, String warnEditId){
        // 存储对象
        List<WarnEditContent> list = new ArrayList<>();
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
                String content = json.getString("content_" + channelId + "_" + areaId);
                WarnEditContent warnEditContent = new WarnEditContent();
                warnEditContent.setWarnEditId(warnEditId);
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
     * @param warnEditId    预警基本信息ID
     * @return
     */
    private int addWarnEditUser(JSONObject json, String warnEditId){
        // 存储对象
        List<WarnEditUser> list = new ArrayList<>();
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
                WarnEditUser warnEditUser = new WarnEditUser();
                warnEditUser.setWarnEditId(warnEditId);
                warnEditUser.setChannelId(channel.getString("channelId"));
                warnEditUser.setUserGroupId(userGroup.getString("userGroupId"));
                warnEditUser.setUserGroupName(userGroupName);
                list.add(warnEditUser);
            }
        }
        return this.warnEditUserMapper.insertList(list);
    }

    /**
     * 4：添加预警编辑流程信息
     * @param json          接收信息
     * @param warnEditId    预警基本信息ID
     * @return
     */
    private int addWarnEditFlow(JSONObject json, String warnEditId){

        String[] flow = json.getString("flow").split(",");

        for(int i = 0; i<flow.length; i++){
            int currentFlow = Integer.parseInt(flow[i]);
            WarnEditFlow warnEditFlow = new WarnEditFlow();
            warnEditFlow.setWarnEditId(warnEditId);
            warnEditFlow.setFlow(currentFlow);
            if(currentFlow == 3 || currentFlow == 4){
                Map<String, Object> map = new HashMap<>();
                map.put("areaId",json.getString("areaId"));
                map.put("organizationId",json.getString("organizationId"));
                map.put("type", currentFlow == 3 ? 2 : 0); // 2:查询同级应急办  // 0:查询同级发布中心
                Organization organization = this.organizationMapper.selectSameGradeByParam(map);
                warnEditFlow.setEmployeeId(null);
                warnEditFlow.setEmployeeName(null);
                warnEditFlow.setOrganizationId(organization.getId());
                warnEditFlow.setOrganizationName(organization.getOrganizationName());
            }else {
                warnEditFlow.setEmployeeId(json.getString("employeeId"));
                warnEditFlow.setEmployeeName(json.getString("employeeName"));
                warnEditFlow.setOrganizationId(json.getString("organizationId"));
                warnEditFlow.setOrganizationName(json.getString("organizationName"));
            }
            warnEditFlow.setAdvice(json.getString("advice"));
            if(i==0){
                warnEditFlow.setIsOption(1);
            }else{
                warnEditFlow.setIsOption(0);
            }

            this.warnEditFlowMapper.insert(warnEditFlow);
        }

        return 1;

    }

    /**
     * 5：添加预警编辑上传文件信息
     * @param json
     * @param warnEditId
     * @return
     */
    private int addWarnEditFile(JSONObject json, String warnEditId){
        List<WarnEditFile> list = new ArrayList<>();
        JSONArray files = json.getJSONArray("files");
        if(files != null){
            for(int i = 0; i<files.size(); i++){
                JSONObject file = files.getJSONObject(i);
                WarnEditFile warnEditFile = new WarnEditFile();
                warnEditFile.setWarnEditId(warnEditId);
                warnEditFile.setName(file.getString("name"));
                warnEditFile.setSize(file.getString("size"));
                warnEditFile.setUrl(file.getString("url"));
                list.add(warnEditFile);
            }
            return this.warnEditFileMapper.insertList(list);
        }
        return 0;
    }

}
