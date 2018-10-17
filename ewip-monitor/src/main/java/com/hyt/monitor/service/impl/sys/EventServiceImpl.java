package com.hyt.monitor.service.impl.sys;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import com.hyt.monitor.entity.sys.Channel;
import com.hyt.monitor.entity.sys.Tree;
import com.hyt.monitor.mapper.sys.EventMapper;
import com.hyt.monitor.service.sys.IEventService;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service("EventService")
public class EventServiceImpl implements IEventService {
	
	@Resource
	private EventMapper eventMapper;

	/**
	 * 预警级别统计
	 */
	@Override
	public JSONObject drawWarnLevelTotal(Map<String, Object> map) {
		JSONObject jsonAll =new JSONObject();
		JSONArray arry =new JSONArray();
		JSONArray arryName =new JSONArray();
		List<Map<String, Object>> basicList=eventMapper.basicColor(map);
		List<Map<String, Object>> totalList=eventMapper.drawWarnLevelTotal(map);
		if(totalList.size()>0){
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
			jsonAll.put("name", arryName);
			jsonAll.put("arry", arry);
			jsonAll.put("message", 1);
		}else{
			jsonAll.put("message", 0);
		}
		return jsonAll;
	}

	/**
	 *
	 * drawPublishAreaTotal(发布地区统计)
	 * @author lxv
	 * @param @param map
	 * @param @return    设定文件
	 * @return JSONObject    返回类型
	 * @throws
	 */
	public JSONObject drawPublishAreaTotal(Map<String, Object> map) {
		List<Map<String,Object>> list = eventMapper.drawPublishAreaTotal(map);
		JSONObject json = new JSONObject();
		if(list.size()>0){
			Set<String> set = new HashSet<String>();
			for (int i=0;i<list.size();i++) {
				set.add(list.get(i).get("city").toString());
			}
			json.put("area", set);
			String[] colors = { "红色", "橙色", "黄色", "蓝色" };
			List[] colorNums = {new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList()};
			//地区
			for (String areaName : set) {
				// 颜色
				int a=0;
				for (int k = 0; k < colors.length; k++) {
					String colornum="0";
					//查询list对比
					for (int j = 0; j < list.size(); j++) {
						if (list.get(j).get("colorLevelName").equals(colors[k])&&list.get(j).get("city").equals(areaName)) {
							colornum=list.get(j).get("count").toString();
						}
					}
					colorNums[k].add(colornum);
					a+=Integer.parseInt(colornum);
				}
				colorNums[4].add(a);
			}
			json.put("red", colorNums[0]);
			json.put("orange", colorNums[1]);
			json.put("yellow", colorNums[2]);
			json.put("blue", colorNums[3]);
			json.put("total", colorNums[4]);
			json.put("message", 1);
		}else{
			json.put("message", 0);
		}
		return json;
	}

	/**
	 * 预警列表
	 */
	@Override
	public JSONObject warnList(Map<String, Object> map) {
		JSONObject json = new JSONObject();
		int warnCount = this.eventMapper.warnListCount(map);
		if(warnCount >0){
			List<Map<String,Object>> list =this.eventMapper.warnList(map);
			json.put("count", warnCount);
			json.put("page", map.get("page"));
			json.put("pageSize", map.get("pageSize"));
			json.put("list", list);
			json.put("message", 1);
		}else{
			json.put("message", 0);
		}
		return json;
	}
	/**
	 * 	已发布预警事件信息
	 */
	public JSONObject alreadyPub(Map<String, Object> map) {
		JSONObject jsonAll = new JSONObject();
		JSONObject jsonZrzha = new JSONObject();
		JSONObject jsonSgzns = new JSONObject();
		JSONObject jsonShaqs = new JSONObject();
		JSONObject jsonGgwss = new JSONObject();
		JSONArray arry = new JSONArray();
		JSONArray arryZrzha = new JSONArray();
		JSONArray arrySgzns = new JSONArray();
		JSONArray arryShaqs = new JSONArray();
		JSONArray arryGgwss = new JSONArray();
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
		List<Map<String, Object>> list = this.eventMapper.sendWarnStats(map);
		//灾种颜色基础信息
		if(list.size() > 0){
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
			int countZrzha=0;
			int countSgzns=0;
			int countShaqs=0;
			int countGgwss=0;
			String nameZrzha="",nameSgzns="",nameShaqs="",nameGgwss="";
			for(Map<String, Object> warn : disClorList){
				String id=warn.get("keyId").toString();
				if(id.equals("1")){
					String count=warn.get("count").toString();
					JSONObject json = new JSONObject();
					json.put("name", warn.get("color").toString());
					json.put("value",count);
					arryZrzha.add(json);
					countZrzha=countZrzha+Integer.parseInt(count);
					nameZrzha=warn.get("typeName").toString();
				}else if(id.equals("2")){
					String count=warn.get("count").toString();
					JSONObject json = new JSONObject();
					json.put("name", warn.get("color").toString());
					json.put("value",warn.get("count").toString());
					arrySgzns.add(json);
					countSgzns=countSgzns+Integer.parseInt(count);
					nameSgzns=warn.get("typeName").toString();
				}else if(id.equals("3")){
					String count=warn.get("count").toString();
					JSONObject json = new JSONObject();
					json.put("name", warn.get("color").toString());
					json.put("value",warn.get("count").toString());
					arryShaqs.add(json);
					countShaqs=countShaqs+Integer.parseInt(count);
					nameShaqs=warn.get("typeName").toString();
				}else if(id.equals("4")){
					String count=warn.get("count").toString();
					JSONObject json = new JSONObject();
					json.put("name", warn.get("color").toString());
					json.put("value",warn.get("count").toString());
					arryGgwss.add(json);
					countGgwss=countGgwss+Integer.parseInt(count);
					nameGgwss=warn.get("typeName").toString();
				}
			}
			jsonZrzha.put("typeName",nameZrzha);
			jsonZrzha.put("id","natural");
			jsonZrzha.put("data",arryZrzha);
			jsonZrzha.put("count",countZrzha);
			jsonSgzns.put("typeName",nameSgzns);

			jsonSgzns.put("id","accident");
			jsonSgzns.put("data",arrySgzns);
			jsonSgzns.put("count",countSgzns);
			jsonShaqs.put("typeName",nameShaqs);

			jsonShaqs.put("id","safety");
			jsonShaqs.put("data",arryShaqs);
			jsonShaqs.put("count",countShaqs);
			jsonGgwss.put("typeName",nameGgwss);

			jsonGgwss.put("id","sanitation");
			jsonGgwss.put("data",arryGgwss);
			jsonGgwss.put("count",countGgwss);
			arry.add(jsonZrzha);
			arry.add(jsonSgzns);
			arry.add(jsonShaqs);
			arry.add(jsonGgwss);
			jsonAll.put("option", arry);
			jsonAll.put("count", warnCount);
			jsonAll.put("message", 1);
		}else{
			jsonAll.put("message", 0);
		}
		return jsonAll;
	}

	/**
	 * 发布渠道统计
	 */
	@Override
	public JSONObject drawPublishChannelTotal(Map<String, Object> map) {
		List<Map<String,Object>> channellist = eventMapper.basicChannel(map);
		List<Map<String,Object>> list = eventMapper.channelStatistics(map);
		JSONObject jsonAll = new JSONObject();
		if(list.size()>0){
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
			jsonAll.put("select", selectjson);
			jsonAll.put("name", names);
			jsonAll.put("arry", arry);
			jsonAll.put("message", 1);
		}else{
			jsonAll.put("message", 0);
		}
		return jsonAll;
	}

	/**
	 * 预警发布趋势
	 * @param map
	 * @return
	 */
	public JSONObject hourWarn(Map<String, Object> map) {
		String startTime = map.get("startTime").toString();
		String endTime = map.get("endTime").toString();
		List<Map<String,Object>> list = eventMapper.hourWarn(map);
		JSONObject json=new JSONObject();
		List[] colorNums = {new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList()};
		String[] colors = { "红色", "橙色", "黄色", "蓝色" };
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
						if (list.get(j).get("colorLevelName").equals(colors[k])&& list.get(j).get("sendTime").equals(arry.get(t))) {
							colornum=list.get(j).get("count").toString();
						}
					}
					colorNums[k].add(colornum);
					a+=Integer.parseInt(colornum);
				}
				colorNums[4].add(a);
			}
			json.put("area", newarry);
			json.put("red", colorNums[0]);
			json.put("orange", colorNums[1]);
			json.put("yellow", colorNums[2]);
			json.put("blue", colorNums[3]);
			json.put("total", colorNums[4]);
			json.put("message", 1);
			return json;
		} catch (Exception e) {
			json.put("message", 0);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 责任人以及受众统计
	 */
	@Override
	public JSONObject personStas(Map<String, Object> map) {
		List<Map<String,Object>> list = eventMapper.disasterSts(map);
		JSONObject json = new JSONObject();
		if(list.size()>0){
			Set<String> set = new HashSet<String>();
			for (int i=0;i<list.size();i++) {
				set.add(list.get(i).get("disasterName").toString());
			}
			json.put("area", set);
			String[] colors = { "红色", "橙色", "黄色", "蓝色" };
			List[] colorNums = {new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList()};
			//地区
			for (String areaName : set) {
				// 颜色
				int a=0;
				for (int k = 0; k < colors.length; k++) {
					String colornum="0";
					//查询list对比
					for (int j = 0; j < list.size(); j++) {
						if (list.get(j).get("colorLevelName").equals(colors[k])&&list.get(j).get("disasterName").equals(areaName)) {
							colornum=list.get(j).get("count").toString();
						}
					}
					colorNums[k].add(colornum);
					a+=Integer.parseInt(colornum);
				}
				colorNums[4].add(a);
			}
			json.put("red", colorNums[0]);
			json.put("orange", colorNums[1]);
			json.put("yellow", colorNums[2]);
			json.put("blue", colorNums[3]);
			json.put("total", colorNums[4]);
			json.put("message", 1);
		}else{
			json.put("message", 0);
		}
		return json;
	}

	/**
	 * 查询所有渠道
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findChannelAll(Map<String, Object> map){
		return this.eventMapper.findChannelAll(map);
	}

	/**
	 * 获取灾种树
	 * @param map
	 * @return
	 */
	public List<Tree> disasterTree(Map<String, Object> map) {
		List<Map<String,Object>> list=eventMapper.disasterTree(map);
		List<Map<String,Object>> newList=new ArrayList<>();
		List<Tree> treeList = new ArrayList<Tree>();
		if(list.size() == 0) return null;
		for(Map<String,Object> dis : list){
			Map<String,Object> newMap=new HashMap<>();
			if(dis.get("config").equals(1)){
				newMap.put("name",dis.get("name").toString()+"("+dis.get("colorLevelName").toString()+")");
			}else{
				newMap.put("name",dis.get("name").toString());
			}
			newMap.put("id",dis.get("id").toString());
			newMap.put("pid",dis.get("pid").toString());
			newMap.put("code",dis.get("code").toString());
			newMap.put("type",dis.get("type").toString());
			newList.add(newMap);
		}
		for(Map<String,Object> disaster : newList){
			Tree tree = new Tree();
			tree.setId(disaster.get("id").toString());
			tree.setpId(disaster.get("pid").toString());
			tree.setName(disaster.get("name").toString());
			tree.setCode(disaster.get("code").toString());
			if(disaster.get("type").toString().equals("0")|| disaster.get("type").toString().equals("1")|| disaster.get("type").toString().equals("-1")){
				tree.setIsParent(true);
				tree.setOpen(true);
			}
			treeList.add(tree);
		}
		return treeList;
	}

	/**
	 * 获取地区树
	 * @param map
	 * @return
	 */
	@Override
	public List<Tree> areaTree(Map<String, Object> map) {
		List<Map<String,Object>> list=eventMapper.areaTree(map);
		List<Tree> treeList = new ArrayList<Tree>();
		if(list.size() == 0) return null;
		for(Map<String,Object> area : list){
			Tree tree = new Tree();
			tree.setId(area.get("id").toString());
			tree.setpId(area.get("pid").toString());
			tree.setName(area.get("areaName").toString());
			tree.setCode(area.get("code").toString());
			if(area.get("level").toString().equals("1")|| area.get("level").toString().equals("2")){
				tree.setIsParent(true);
				tree.setOpen(true);
			}
			treeList.add(tree);
		}
		return treeList;
	}

	/**
	 * 查询预警信息
	 * @param map
	 * @return
	 */
	public JSONArray getWarnData(Map<String, Object> map) {
		List<Map<String,Object>> list=eventMapper.getWarnData(map);
		JSONArray array=new JSONArray();
		if(list.size()>0){
			for(Map<String,Object> newMap:list){
				JSONObject json=new JSONObject();
				json.put("taskId",newMap.get("taskId"));
				json.put("areaId",newMap.get("areaId"));
				json.put("areaName",newMap.get("areaName"));
				json.put("content",newMap.get("content"));
				json.put("sendTime",newMap.get("sendTime"));
				json.put("orgName",newMap.get("orgName"));
				json.put("longitude",newMap.get("longitude"));
				json.put("latitude",newMap.get("latitude"));
				json.put("title",newMap.get("title"));
				json.put("icon",newMap.get("icon"));
				array.add(json);
			}
			return array;
		}else{
			return null;
		}
	}
	/**
	 * 根据ID和地区ID获取发布的渠道
	 * @param map
	 * @return
	 */
	@Override
	public JSONObject channelsByIdArea(Map<String, Object> map) {
		JSONObject json = new JSONObject();
		List<Map<String,Object>> list=eventMapper.channelsByIdArea(map);
		String channel="";
		for (int i=0;i<list.size();i++){
			channel += list.get(i).get("name") + ",";
		}
		channel=channel.substring(0, channel.length() - 1);
		json.put("channel",channel);
		return json;
	}

	public static void main(String[] args) {

	}
}
