package com.hyt.monitor.service.monitor;


import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface IPublishStatisticsService {


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
	 JSONObject personStas(Map<String, Object> map) throws Exception;
}

