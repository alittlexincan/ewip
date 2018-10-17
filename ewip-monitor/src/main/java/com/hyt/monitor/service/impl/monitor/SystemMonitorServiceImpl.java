package com.hyt.monitor.service.impl.monitor;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.monitor.mapper.monitor.ISystemMonMapper;
import com.hyt.monitor.service.monitor.SystemMonitorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service("systemMonitorService")
public class SystemMonitorServiceImpl implements SystemMonitorService {

	@Resource
	private ISystemMonMapper sysMonMapper;

    /**
     * 网络监控系统运行情况
     */
    @Override
    public JSONObject systemMonitoring(Object object) {
        JSONObject json = new JSONObject();
        List<Map<String,Object>> list = sysMonMapper.systemMonitoring(null);
        int normal=0;
        int abnormal=0;
        for(Map ma :list){
            if(ma.get("resultType").toString().equals("1")){
                normal=normal+1;
            }else{
                abnormal=abnormal+1;
            }
        }
        json.put("normalNum", normal);
        json.put("abnormalNum", abnormal);
        json.put("list", list);
        return json;
    }


    /**
     * 网络监控终端监控
     */
    @Override
    public JSONObject warnTerminalMonitor(Map<String, Object> map) {
        JSONObject jsonall =new JSONObject();
        JSONArray allArry = new JSONArray();
        JSONArray arry =new JSONArray();
        List<Map<String, Object>> basicList=sysMonMapper.terminalMonitorBasic(null);
        List<Map<String, Object>> totalList=sysMonMapper.warnTerminalMonitor(map);
        Set<String> set = new HashSet<String>();
        for (int i=0;i<basicList.size();i++) {
            set.add(basicList.get(i).get("keyId").toString()+","+basicList.get(i).get("name").toString());
        }
        for(int i=0;i<basicList.size();i++){
            JSONObject json =new JSONObject();
            json.put("name", basicList.get(i).get("NAME").toString());
            json.put("value", basicList.get(i).get("COUNT").toString());
            json.put("state", basicList.get(i).get("serviceState").toString());
            arry.add(json);
        }
        for(int i=0;i<arry.size();i++){
            JSONObject obj=arry.getJSONObject(i);
            int count=Integer.parseInt(obj.get("value").toString());
            String channelName=obj.get("name").toString();
            String state=obj.get("state").toString();
            for(int j=0;j<totalList.size();j++){
                String name=totalList.get(j).get("name").toString();
                String serviceState=totalList.get(j).get("serviceState").toString();
                if(channelName.equals(name) && serviceState.equals(state)){
                    obj.put("value", Integer.parseInt(totalList.get(j).get("count").toString()));
                }
            }
        }
        for(String channelName : set){
            String channel[] = channelName.toString().split(",");
            JSONArray channelarry = new JSONArray();
            String name=channel[1];
            String channelId=channel[0];
            int totalNum=0;
            JSONObject jsonName = new JSONObject();
            for(int i=0; i<arry.size();i++ ){
                JSONObject json = new JSONObject();
                JSONObject obj=arry.getJSONObject(i);
                if(name.equals(obj.get("name"))){
                    json.put("name", obj.get("state").toString());
                    json.put("value", obj.get("value").toString());
                    totalNum=totalNum+Integer.parseInt(obj.get("value").toString());
                    channelarry.add(json);
                }
            }
            jsonName.put("channelId", channelId);
            jsonName.put("name", name);
            jsonName.put("totalNum", totalNum);
            jsonName.put("list", channelarry);
            allArry.add(jsonName);
        }
        jsonall.put("list", allArry);
        return jsonall;
    }



}
