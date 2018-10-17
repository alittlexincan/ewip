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


    @Autowired
    private IWarnEditOptionMapper warnEditOptionMapper;

    @Autowired
    private IWarnEditFlowMapper warnEditFlowMapper;



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

        // 1：获取预警基本信息
        JSONObject result = getWarnEditInfo(map);

        // 2：获取预警内容
        getWarnEditContentInfo(result, map);

        // 3：获取预警发布对象
        getWarnEditUserInfo(result, map);

        // 4：获取预警上传文件
        getWarnEditFileInfo(result, map);

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

        JSONObject result = new JSONObject();

        int currentFlow = Integer.parseInt(map.get("currentFlow").toString());

        this.warnEditFlowMapper.updateFlow(map);

        // 修改小于或等于当前流程状态为1
        Map<String, Object> option = new HashMap<>();
        option.put("id", map.get("warnEditId"));
        option.put("flow", currentFlow);
        option.put("isOption",1);
        this.warnEditFlowMapper.updateOption(option);

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

            // 如果status==1说明发布中心发布了此预警，此时更改预警基础信息表，预警状态为已发布
            map.put("id", map.get("warnEditId"));
            map.put("status", 1);
            this.warnEditMapper.updateStatus(map);

            // 读取预警信息，即将推送到分发服务
            // 1：获取预警基本信息
            result = getWarnEditInfo(map);

            // 2：获取预警内容
            getWarnEditContentInfo(result, map);

            // 3：获取预警发布对象
            getWarnEditUserInfo(result, map);

            // 4：获取预警上传文件
            getWarnEditFileInfo(result, map);

            result.put("status", currentFlow);
            result.put("msg", "发布成功");
            result.put("employeeId",map.get("id"));
            result.put("employeeName",map.get("name"));
            result.put("organizationId",map.get("organizationId"));
            result.put("organizationName",map.get("organizationName"));
            result.put("organizationCode",map.get("organizationCode"));

        }

        return result;
    }

    /**
     * 修改预警状态
     *
     * @param map
     * @return
     */
    @Override
    public int updateStatus(Map<String, Object> map) {
        return this.warnEditMapper.updateStatus(map);
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

            List<JSONObject> channels = new ArrayList<>();

            List<JSONObject> areas = new ArrayList<>();

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
            Map<String, List<WarnEditContent>> content = list.stream().collect(Collectors.groupingBy(WarnEditContent::getChannelId));

            result.put("channel",channelArray);
            result.put("area",areaArray);
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
                        .filter(p -> p.getUserGroupId().equals(weu.getUserGroupId()))
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
            result.put("user", user);
            result.put("group", group);
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
                json.put("name", file.getName());
                json.put("size", file.getSize());
                fileArray.add(json);
            });
            result.put("files",fileArray);
        }else {
            result.put("files", new JSONArray());
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

    @Override
    public List<Map<String, Object>> getWechatWarnInfo(){
        return this.warnEditOptionMapper.getWechatWarnInfo();
    }
}
