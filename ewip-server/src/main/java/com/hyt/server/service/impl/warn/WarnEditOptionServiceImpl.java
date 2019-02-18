package com.hyt.server.service.impl.warn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.page.MybatisPage;
import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.warn.*;
import com.hyt.server.mapper.warn.*;
import com.hyt.server.service.warn.IWarnEditOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: XincanJiang
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("warnEditOptionService")
public class WarnEditOptionServiceImpl extends AbstractService<WarnEditOption> implements IWarnEditOptionService {

    /**
     * 预警基本信息数据访问层
     */
    @Autowired
    private IWarnEditMapper warnEditMapper;

    /**
     * 预警内容信息数据访问层
     */
    @Autowired
    private IWarnEditContentMapper warnEditContentMapper;

    /**
     * 预警发布对象信息数据访问层
     */
    @Autowired
    private IWarnEditUserMapper warnEditUserMapper;

    /**
     * 预警上传信息数据访问层
     */
    @Autowired
    private IWarnEditFileMapper warnEditFileMapper;

    /**
     * 预警流程数据访问层
     */
    @Autowired
    private IWarnEditFlowMapper warnEditFlowMapper;

    /**
     * 预警信息操作数据访问层
     */
    @Autowired
    private IWarnEditOptionMapper warnEditOptionMapper;

    /**
     * 根据条件查询预警编辑流程信息
     * @param map
     * @return
     */
    @Override
    public PageInfo<WarnEditOption> selectFlowByParam(Map<String, Object> map) {
        MybatisPage.getPageSize(map);
        PageHelper.startPage(MybatisPage.page, MybatisPage.limit);
        List<WarnEditOption> areaList = this.warnEditOptionMapper.selectFlowByParam(map);
        return new PageInfo<>(areaList);
    }

    /**
     * 查询预警发布后详细信息
     * @param map
     * @return
     */
    public JSONObject selectWarnEditDetail(Map<String, Object> map){
        JSONObject result = getWarnEditInfo(map);   // 1：获取预警基本信息
        getWarnEditContentInfo(result, map);        // 2：获取预警内容
        getWarnEditUserInfo(result, map);           // 3：获取预警发布对象
        getWarnEditFileInfo(result, map);           // 4：获取预警上传文件
        return result;
    }

    /**
     * 添加流程信息
     * warnEditFlow
     * @param map
     * @return
     */
    @Override
    @Transactional
    public JSONObject insert(Map<String, Object> map) {
        // 存储返回的数据信息
        JSONObject result = new JSONObject();
        // 获取基本信息
        String warnEditId = map.get("warnEditId").toString()
                ,employeeId = map.get("employeeId").toString()
                ,employeeName = map.get("employeeName").toString()
                ,organizationId = map.get("organizationId").toString()
                ,msgType = map.get("msgType").toString()
                ,organizationName = map.get("organizationName").toString();

        // 获取总流程
        String[] flow = map.get("flow").toString().split(",");
        // 获取当前流程
        int currentFlow = Integer.parseInt(map.get("currentFlow").toString());
        // 提取当前流程下标
        int flowIndex = 0;
        for(int i =0 ;i < flow.length; i++) {
            if (flow[i].equals(currentFlow+""))  flowIndex = i;
        }
        // 根据当前流程下标，获取下一个流程
        int nextFlow = flowIndex + 1 >= flow.length ? currentFlow : Integer.parseInt(flow[flowIndex + 1]);
        // 组装下一个流程信息
        if(currentFlow != 4){
            WarnEditFlow warnEditFlow = new WarnEditFlow();
            warnEditFlow.setWarnEditId(warnEditId);
            warnEditFlow.setFlow(nextFlow);
            warnEditFlow.setEmployeeId(employeeId);
            warnEditFlow.setEmployeeName(employeeName);
            warnEditFlow.setOrganizationId(organizationId);
            warnEditFlow.setOrganizationName(organizationName);
            // 如果当前流程为发布流程，则直接插入1，否则插入0
            warnEditFlow.setIsOption(currentFlow == 4 ? 1 : 0);
            warnEditFlow.setAdvice(map.get("advice").toString());
            // 插入下一个流程信息
            this.warnEditFlowMapper.insert(warnEditFlow);
        }
        // 当前流程操作状态
        map.put("isOption",1);
        // 修改当前流程操作状态和操作信息
        this.warnEditFlowMapper.updateFlow(map);
        // 流程操作完毕返回的数据信息
        if(currentFlow == 1){
            result.put("status", currentFlow);
            result.put("msg", "审核成功");
        }
        if(currentFlow == 2){
            result.put("status", currentFlow);
            result.put("msg", "签发成功");
        }
        if(currentFlow == 3){
            result.put("status", currentFlow);
            result.put("msg", "应急办签发成功");
        }
        if(currentFlow == 4){
            // 更改预警编辑信息主表状态
            Map<String, Object> updateWarnEditStatus = new HashMap<>();
            updateWarnEditStatus.put("id", warnEditId);
            if(msgType!=null && msgType.equals("Cancel")){
                updateWarnEditStatus.put("status", 2);
            }else{
                updateWarnEditStatus.put("status", 1);
            }
            this.warnEditMapper.updateStatus(updateWarnEditStatus);
            // 读取预警信息，即将推送到分发服务
            // 1：获取预警基本信息
            result = getWarnEditInfo(map);
            // 2：获取预警内容
            getWarnEditContentInfo(result, map);
            // 3：获取预警发布对象
            getWarnEditUserInfo(result, map);
            // 4：获取预警上传文件
            getWarnEditFileInfo(result, map);
            //组装返回信息
            result.put("status", currentFlow);
            result.put("msg", "发布成功");
            result.put("employeeId",employeeId);
            result.put("employeeName",employeeName);
            result.put("organizationId",organizationId);
            result.put("organizationName",organizationName);
            result.put("organizationCode",map.get("organizationCode"));
        }
        return result;
    }

    /**
     * 预警：驳回操作
     * @param map
     * @return
     */
    @Override
    @Transactional
    public int reject(Map<String, Object> map) {

        JSONObject json = new JSONObject(map);
        // 1：删除当前此条预警流程
        this.warnEditFlowMapper.deleteFlowById(map);

        // 2：插入驳回预警流程(0：录入；1：审核；2：签发；3：应急办签发；4：发布；5：保存代发；6：驳回; 7：终止)
        WarnEditFlow warnEditFlow = new WarnEditFlow();
        warnEditFlow.setWarnEditId(json.getString("warnEditId"));
        warnEditFlow.setFlow(6);
        warnEditFlow.setEmployeeId(json.getString("employeeId"));
        warnEditFlow.setEmployeeName(json.getString("employeeName"));
        warnEditFlow.setOrganizationId(json.getString("organizationId"));
        warnEditFlow.setOrganizationName(json.getString("organizationName"));
        // 如果当前流程为发布流程，则直接插入1，否则插入0
        warnEditFlow.setIsOption(0);
        warnEditFlow.setAdvice(json.getString("advice"));
        this.warnEditFlowMapper.insert(warnEditFlow);
        return 1;
    }

    /**
     * 根据id获取预警基本信息
     * @param map
     * @return
     */
    private JSONObject getWarnEditInfo(Map<String, Object> map){
        WarnEdit warnEdit = this.warnEditMapper.selectByWarnEditId(map);
        return (JSONObject) JSON.toJSON(warnEdit);
    }

    /**
     * 根据id获取预警内容信息
     * @param result
     * @param map
     */
    private void getWarnEditContentInfo (JSONObject result, Map<String, Object> map){
        List<WarnEditContent> list = this.warnEditContentMapper.selectByWarnEditId(map);
        if (list.size() > 0){
            // 组装渠道信息
            List<JSONObject> channels = new ArrayList<>();
            // 组装地区信息
            List<JSONObject> areas = new ArrayList<>();
            // 提取渠道，地区信息
            for(WarnEditContent wec : list){
                // 组装发布渠道 去重
                JSONObject channel = new JSONObject();
                channel.put("channelId",wec.getChannelId());
                channel.put("channelName",wec.getChannelName());
                channel.put("channelCode",wec.getChannelCode());
                channels.add(channel);
                // 组装影响地区 去重
                JSONObject area = new JSONObject();
                area.put("areaId",wec.getAreaId());
                area.put("areaName",wec.getAreaName());
                area.put("areaCode",wec.getAreaCode());
                areas.add(area);
            }
            // 渠道数据处理
            List<String> channelId = new ArrayList<>();
            List<JSONObject> channelArray = channels.stream().filter(// 过滤去重
                    channel -> {
                        boolean flag = !channelId.contains(channel.getString("channelId"));
                        channelId.add(channel.getString("channelId"));
                        return flag;
                    }
            ).collect(Collectors.toList());
            // 地区数据处理
            List<String> areaId = new ArrayList<>();
            List<JSONObject> areaArray = areas.stream().filter(// 过滤去重
                    area -> {
                        boolean flag = !areaId.contains(area.getString("areaId"));
                        areaId.add(area.getString("areaId"));
                        return flag;
                    }
            ).collect(Collectors.toList());
            // 预警内容处理
            // Map<String, List<WarnEditContent>> content = list.stream().collect(Collectors.groupingBy(WarnEditContent::getChannelId));
            Map<String, String> contents = new HashMap<>();

            String content = list.get(0).getContent();
            list.forEach(message -> {
                contents.put(message.getChannelId(), message.getContent());
            });
            result.put("channels",channelArray);
            result.put("areas",areaArray);
            result.put("contents",contents);
            result.put("content",content);
        }
    }

    /**
     * 根据id获取预警发布对象信息
     * @param result
     * @param map
     * @return
     */
    private void getWarnEditUserInfo(JSONObject result, Map<String, Object> map){
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

    /**
     * 根据id获取预警上传文件信息
     * @param map
     * @return
     */
    private void getWarnEditFileInfo(JSONObject result, Map<String, Object> map){
        List<WarnEditFile> list = this.warnEditFileMapper.selectByWarnEditId(map);
        if(list.size() > 0){
            JSONArray fileArray = new JSONArray();
            list.forEach(file -> {
                JSONObject json = new JSONObject();
                json.put("id", file.getId());
                json.put("name", file.getName());
                json.put("size", file.getSize());
                json.put("url", file.getUrl());
                fileArray.add(json);
            });
            result.put("files",fileArray.toJSONString());
        }else {
            result.put("files", "");
        }
    }

    /**
     * 根据预警ID查询对应的预警流程
     * @param map
     * @return
     */
    @Override
    public List<WarnEditFlow> selectFlowByWarnEditId(Map<String, Object> map){
        return this.warnEditFlowMapper.selectFlowByWarnEditId(map);
    }

    /**
     * 给微信平台提供预警信息
     * @return
     */
    @Override
    public List<Map<String, Object>> getWechatWarnInfo(){
        return this.warnEditOptionMapper.getWechatWarnInfo();
    }

    /**
     * 查询首页预警个数
     * @param map
     * @return
     * lxv
     */
    @Override
    public JSONArray selectWarn(Map<String, Object> map) {
        JSONArray arry=new JSONArray();
        List<Map<String, Object>> list=this.warnEditOptionMapper.selectWarn(map);
        for(int i=0;i< list.size();i++){
            String flow=list.get(i).get("flow").toString();
            String count=list.get(i).get("count").toString();
            JSONObject json =new JSONObject();
            if(flow.equals("1")){
                json.put("flow",1);
                json.put("count",count);
            }else if(flow.equals("2")){
                json.put("flow",2);
                json.put("count",count);
            }else if(flow.equals("3")){
                json.put("flow",3);
                json.put("count",count);
            }else if(flow.equals("4")){
                json.put("flow",4);
                json.put("count",count);
            }else if(flow.equals("6")){
                json.put("flow",6);
                json.put("count",count);
            }
            arry.add(json);
        }
        return arry;
    }
}
