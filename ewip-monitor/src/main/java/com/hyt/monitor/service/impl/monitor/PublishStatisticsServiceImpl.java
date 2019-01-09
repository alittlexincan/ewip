package com.hyt.monitor.service.impl.monitor;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.monitor.mapper.monitor.PublishStatisticsMapper;
import com.hyt.monitor.service.monitor.IPublishStatisticsService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("PublishStatisticsService")
@SuppressWarnings("all")
public class PublishStatisticsServiceImpl implements IPublishStatisticsService {

	@Resource
	private PublishStatisticsMapper publishStsMapper;

	/**
	 * 预报类型统计
	 */
	@Override
	public JSONObject drawWarnLevelTotal(Map<String, Object> map) {
		JSONObject jsonall =new JSONObject();
		JSONArray arry =new JSONArray();
		JSONArray arryName =new JSONArray();
		List<Map<String, Object>> basicList=publishStsMapper.basicColor(map);
		List<Map<String, Object>> totalList=publishStsMapper.drawWarnLevelTotal(map);
		for(int i=0;i<basicList.size();i++){
			JSONObject json =new JSONObject();
			json.put("name", basicList.get(i).get("name").toString());
			json.put("value", basicList.get(i).get("count").toString());
			arry.add(json);
			arryName.add(basicList.get(i).get("name").toString());
		}
		for(int i=0;i<arry.size();i++){
			JSONObject obj=arry.getJSONObject(i);
			int count=Integer.parseInt(obj.get("value").toString());
			String colorId=obj.get("name").toString();
			for(int j=0;j<totalList.size();j++){
				String id=totalList.get(j).get("colorLevelName").toString();
				if(colorId.equals(id)){
					obj.put("value", Integer.parseInt(totalList.get(j).get("count").toString()));
				}
			}
		}
		jsonall.put("name", arryName);
		jsonall.put("arry", arry);
		return jsonall;
	}


	/**
	 *
	 * drawPublishAreaTotal(发布地区统计)
	 * @author lxv
	 * @Title: drawPublishAreaTotal
	 * @param @param map
	 * @param @return    设定文件
	 * @return JSONObject    返回类型
	 * @throws
	 */
	public JSONObject drawPublishAreaTotal(Map<String, Object> map) {
		List<Map<String,Object>> list = publishStsMapper.drawPublishAreaTotal(map);
		JSONObject json = new JSONObject();
		Set<String> set = new HashSet<String>();
		for (int i=0;i<list.size();i++) {
			set.add(list.get(i).get("city").toString());
		}
		json.put("area", set);
		String[] colors = { "短期预报", "中期预报", "长期预报", "气象专题专报","重大气象专题专报" };
		List[] colorNums = {new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList()};
		//地区
		for (String areaName : set) {
			// 颜色
			int a=0;
			for (int k = 0; k < colors.length; k++) {
				String colornum="0";
				//查询list对比
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j).get("colorLevelName").equals(colors[k]) && list.get(j).get("city").equals(areaName)) {
						colornum=list.get(j).get("count").toString();
					}
				}
				colorNums[k].add(colornum);
				a+=Integer.parseInt(colornum);
			}
			colorNums[5].add(a);
		}
		json.put("red", colorNums[0]);
		json.put("orange", colorNums[1]);
		json.put("yellow", colorNums[2]);
		json.put("blue", colorNums[3]);
		json.put("green", colorNums[4]);
		json.put("total", colorNums[5]);
		return json;
	}

	/**
	 * 24小时预警列表
	 */
	@Override
	public JSONObject warnList(Map<String, Object> map) {
		JSONObject json = new JSONObject();
		int warnCount = this.publishStsMapper.warnListCount(map);
		List<Map<String,Object>> list =this.publishStsMapper.warnList(map);
		json.put("count", warnCount);
		json.put("page", map.get("page"));
		json.put("pageSize", map.get("pageSize"));
		json.put("list", list);
		return json;
	}
	/**
	 * 	已发布信息
	 */
	public JSONObject alreadyPub(Map<String, Object> map) {
		List<Map<String,Object>> list = publishStsMapper.sendWarnStats(map);
		JSONObject json = new JSONObject();
		Set<String> set = new HashSet<String>();
		for (int i=0;i<list.size();i++) {
			set.add(list.get(i).get("name").toString());
		}
		json.put("area", set);
		String[] colors = { "短期预报", "中期预报", "长期预报", "气象专题专报","重大气象专题专报" };
		List[] colorNums = {new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList()};
		//地区
		for (String areaName : set) {
			// 类别
			int a=0;
			for (int k = 0; k < colors.length; k++) {
				String colornum="0";
				//查询list对比
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j).get("type").equals(colors[k]) && list.get(j).get("name").equals(areaName)) {
						colornum=list.get(j).get("count").toString();
					}
				}
				colorNums[k].add(colornum);
				a+=Integer.parseInt(colornum);
			}
			colorNums[5].add(a);
		}
		json.put("red", colorNums[0]);
		json.put("orange", colorNums[1]);
		json.put("yellow", colorNums[2]);
		json.put("blue", colorNums[3]);
		json.put("green", colorNums[4]);
		json.put("total", colorNums[5]);
		return json;

	/*	JSONObject jsonall = new JSONObject();
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		JSONObject json3 = new JSONObject();
		JSONObject json4 = new JSONObject();
		JSONArray arry = new JSONArray();
		JSONArray arry1 = new JSONArray();
		JSONArray arry2 = new JSONArray();
		JSONArray arry3 = new JSONArray();
		JSONArray arry4 = new JSONArray();
				String[] colors = { "红色", "橙色", "黄色", "蓝色" };
		String[] name = { "自然灾害", "事故灾难", "社会安全事件", "公共卫生事件" };
		List<Map<String, Object>> disClorList=new ArrayList();
		for (int i=0;i<name.length;i++){
			String typeName=name[i];
			for (int j=0;j<colors.length;j++){
				Map<String,Object> colorMap=new HashMap<>();
				colorMap.put("keyId",i+1);
				colorMap.put("typeName",typeName);
				colorMap.put("color",colors[j]);
				colorMap.put("count",0);
				disClorList.add(colorMap);
			}
		}
		//查询发送的预警
		List<Map<String, Object>> list = this.publishStsMapper.sendWarnStats(map);
		//灾种颜色基础信息
//		List<Map<String, Object>> disClorList =eventMapper.disColor();
		for(Map<String, Object> jichu : disClorList){
			for(Map<String, Object> yijing : list){
				String type = yijing.get("type").toString();
				String colorLevelName = yijing.get("colorLevelName").toString();
				if(jichu.get("typeName").toString().equals(type) && jichu.get("color").toString().equals(colorLevelName) ){
					jichu.put("count", yijing.get("count"));
				}
			}
		}
		int warnCount=0;
		for(Map<String, Object> warn : disClorList){
			String id=warn.get("count").toString();
			warnCount =warnCount+Integer.parseInt(id);
		}
		int count1=0;
		int count2=0;
		int count3=0;
		int count4=0;
		String name1="",name2="",name3="",name4="";
		for(Map<String, Object> warn : disClorList){
			String id=warn.get("keyId").toString();
			if(id.equals("1")){
				String count=warn.get("count").toString();
				JSONObject json = new JSONObject();
				json.put("name", warn.get("color").toString());
				json.put("value",count);
				arry1.add(json);
				count1=count1+Integer.parseInt(count);
				name1=warn.get("typeName").toString();
			}else if(id.equals("2")){
				String count=warn.get("count").toString();
				JSONObject json = new JSONObject();
				json.put("name", warn.get("color").toString());
				json.put("value",warn.get("count").toString());
				arry2.add(json);
				count2=count2+Integer.parseInt(count);
				name2=warn.get("typeName").toString();
			}else if(id.equals("3")){
				String count=warn.get("count").toString();
				JSONObject json = new JSONObject();
				json.put("name", warn.get("color").toString());
				json.put("value",warn.get("count").toString());
				arry3.add(json);
				count3=count3+Integer.parseInt(count);
				name3=warn.get("typeName").toString();
			}else if(id.equals("4")){
				String count=warn.get("count").toString();
				JSONObject json = new JSONObject();
				json.put("name", warn.get("color").toString());
				json.put("value",warn.get("count").toString());
				arry4.add(json);
				count4=count4+Integer.parseInt(count);
				name4=warn.get("typeName").toString();
			}
		}
		json1.put("typeName",name1);
		json1.put("id","natural");
		json1.put("data",arry1);
		json1.put("count",count1);
		json2.put("typeName",name2);
		json2.put("id","accident");
		json2.put("data",arry2);
		json2.put("count",count2);
		json3.put("typeName",name3);
		json3.put("id","safety");
		json3.put("data",arry3);
		json3.put("count",count3);
		json4.put("typeName",name4);
		json4.put("id","sanitation");
		json4.put("data",arry4);
		json4.put("count",count4);
		arry.add(json1);
		arry.add(json2);
		arry.add(json3);
		arry.add(json4);
		jsonall.put("option", arry);
		jsonall.put("count", warnCount);
		return jsonall;*/
	}


	/**
	 * 发布渠道统计
	 */
	@Override
	public JSONObject drawPublishChannelTotal(Map<String, Object> map) {
		List<Map<String,Object>> channellist = publishStsMapper.basicChannel(map);
		List<Map<String,Object>> list = publishStsMapper.channelStatistics(map);
		JSONObject jsonall = new JSONObject();
		JSONArray names=new JSONArray();
		JSONArray arry=new JSONArray();
		for(int i=0;i<channellist.size();i++){
			JSONObject json = new JSONObject();
			names.add(channellist.get(i).get("name").toString());
			json.put("name", channellist.get(i).get("name").toString());
			json.put("value", channellist.get(i).get("count").toString());
			arry.add(json);
		}

		JSONObject selectjson = new JSONObject();
		for(int i=0;i<arry.size();i++){
			JSONObject obj=arry.getJSONObject(i);
			int count=Integer.parseInt(obj.get("value").toString());
			String channelName=obj.get("name").toString();
				for(int j=0;j<list.size();j++){
					String name=list.get(j).get("channelName").toString();
					if(channelName.equals(name)){
						obj.put("value", Integer.parseInt(list.get(j).get("count").toString()));
					}
				}
				if(obj.get("value").toString().equals("0")){
					selectjson.put(channelName, false);
				}else{
					selectjson.put(channelName, true);
				}
		}
		jsonall.put("select", selectjson);
		jsonall.put("name", names);
		jsonall.put("arry", arry);
		return jsonall;
	}

	/**
	 * 24小时每小时预警趋势
	 * @param map
	 * @return
	 */
	public JSONObject hourWarn(Map<String, Object> map) {
		String startTime = map.get("startTime").toString();
		String endTime = map.get("endTime").toString();
		List<Map<String,Object>> list = publishStsMapper.hourWarn(map);
		JSONObject json=new JSONObject();
		List[] colorNums = {new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList()};
		String[] colors = {"短期预报", "中期预报", "长期预报", "气象专题专报","重大气象专题专报"};
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		JSONArray arry= new JSONArray();
		JSONArray newarry= new JSONArray();
		JSONArray dataArry= new JSONArray();
		try {
			Date startTime1= format2.parse(startTime);
			Date endTime1 = format2.parse(endTime);
			Calendar tempStart = Calendar.getInstance();
			tempStart.setTime(endTime1);
			while (startTime1.getTime() <= endTime1.getTime()) {
				arry.add(format2.format(endTime1));
				tempStart.add(Calendar.DATE, -1);
				endTime1 = tempStart.getTime();
			}
			//时间
			for (int t=arry.size()-1;t>= 0;t--) {
				newarry.add(arry.get(t).toString());
				int a=0;
				for (int k = 0; k < colors.length; k++) {
					String colornum="0";
					//查询list对比
					for (int j = 0; j < list.size(); j++) {
						if (list.get(j).get("type").equals(colors[k])&& list.get(j).get("sendTime").equals(arry.get(t))) {
							colornum=list.get(j).get("count").toString();
						}
					}
					colorNums[k].add(colornum);
					a+=Integer.parseInt(colornum);
				}
				colorNums[5].add(a);
			}
			json.put("area", newarry);
			json.put("red", colorNums[0]);
			json.put("orange", colorNums[1]);
			json.put("yellow", colorNums[2]);
			json.put("blue", colorNums[3]);
			json.put("green", colorNums[4]);
			json.put("total", colorNums[5]);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 责任人以及受众统计
	 */
	@Override
	public JSONObject personStas(Map<String, Object> map) {
		JSONObject json= new JSONObject();
		JSONArray channelArry=new JSONArray();
		JSONArray numArry=new JSONArray();
		List<Map<String,Object>> channelUserList = publishStsMapper.channelUserList(map);
		for(Map<String,Object> list: channelUserList){
			channelArry.add(list.get("name"));
			numArry.add(list.get("num"));
		}
		json.put("channel",channelArry);
		json.put("num",numArry);
		return json;
	}

	public static void main(String[] args) {
		String[] colors = { "红色", "橙色", "黄色", "蓝色" };
		String[] name = { "自然灾害", "事故灾难", "社会安全事件", "公共卫生事件" };
		JSONArray arry =new JSONArray();
		List disClorList=new ArrayList();
		for (int i=0;i<name.length;i++){
			String typeName=name[i];
			for (int j=0;j<colors.length;j++){
				Map<String,Object> map=new HashMap<>();
				map.put("keyId",i+1);
				map.put("typeName",typeName);
				map.put("color",colors[j]);
				map.put("count",0);
//				JSONObject json =new JSONObject();
//				json.put("keyId",i+1);
//				json.put("typeName",typeName);
//				json.put("color",colors[j]);
//				json.put("count",0);
//				arry.add(json);
				disClorList.add(map);
			}
		}
		System.out.println(disClorList);
	}

}
