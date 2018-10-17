package com.hyt.monitor.service.sys;


import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.monitor.entity.sys.Channel;
import com.hyt.monitor.entity.sys.Tree;
import com.hyt.monitor.entity.sys.ZTree;

public interface IEventService {

	/**
	 * 预警级别统计
	 * @param map
	 * @return
	 * @throws Exception
	 */
	JSONObject drawWarnLevelTotal(Map<String, Object> map);

	/**
	 * 发布地区统计
	 * @param map
	 * @return
	 * @throws Exception
	 */
	JSONObject drawPublishAreaTotal(Map<String, Object> map);

	/**
	 *
	 * warnList(24小时预警列表)
	 * @author lxv
	 * @Title: warnList
	 * @param @param map
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	JSONObject warnList(Map<String, Object> map);

	/**
	 * 已发布预警
	 * @param map
	 * @return
	 * @throws Exception
	 */
	 JSONObject alreadyPub(Map<String, Object> map) ;

	 /**
	 * 发布渠道统计
	 * @param map
	 * @return
	 * @throws Exception
	 */
	 JSONObject drawPublishChannelTotal(Map<String, Object> map);

	/**
	 *
	 * hourWarn(24小时每小时预警数)
	 * @author lxv
	 * @Title: hourWarn
	 * @param @param map
	 * @param @return    设定文件
	 * @return JSONObject    返回类型
	 * @throws
	 */
	 JSONObject hourWarn(Map<String, Object> map);

	/**
	 * 责任人受众统计
	 * @param map
	 * @return
	 * @throws Exception
	 */
	 JSONObject personStas(Map<String, Object> map);

	/**
	 * 获取所有渠道
	 * findChannelAll(这里用一句话描述这个方法的作用)
	 * @author lxv
	 * @Title: findChannelAll
	 * @param @param object
	 * @param @return    设定文件
	 * @return List<Channel>    返回类型
	 * @throws
	 */
	 List<Channel> findChannelAll(Map<String, Object> map);
	/**
	 * 获取发布预警
	 * @param map
	 * @return
	 */
	JSONArray getWarnData(Map<String,Object> map);
	/**
	 * 获取地区树
	 * @param map
	 * @return
	 */
	List<Tree> areaTree(Map<String,Object> map);
	/**
	 * 获取灾种数据
	 * @param map
	 * @return
	 */
	List<Tree> disasterTree(Map<String,Object> map);
	/**
	 * 根据ID和地区ID获取发布的渠道
	 * @param map
	 * @return
	 */
	JSONObject channelsByIdArea(Map<String,Object> map);

}

