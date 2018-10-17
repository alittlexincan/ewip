package com.hyt.monitor.mapper.sys;

import com.hyt.monitor.entity.sys.Channel;
import java.util.List;
import java.util.Map;

public interface EventMapper {

	/**
	 * 基础颜色数据
	 * @Title: basicColor
	 * @param @param map
	 */
	 List<Map<String, Object>> basicColor(Map<String, Object> map);
	/**
	 * 预警级别统计
	 * @Title: drawWarnLevelTotal
	 * @param @param map
	 * @param @return    设定文件
	 */
	 List<Map<String, Object>> drawWarnLevelTotal(Map<String, Object> map);

	/**
	 * 发布地区统计
	 * @Title: drawPublishAreaTotal
	 * @param @param map
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	List<Map<String, Object>> drawPublishAreaTotal(Map<String, Object> map);

	/**
	 * 24小时预警列表
	 */
	List<Map<String, Object>> warnList(Map<String, Object> map);
	/**
	 * 24小时预警列表个数
	 */
	int warnListCount(Map<String, Object> map);
	/**
	 *
	 * sendWarnStats(查询预警颜色数量)
	 * @Title: sendWarnStats
	 * @param @param map
	 * @param @return    设定文件
	 * @return List<Object>    返回类型
	 * @throws
	 */
	 List<Map<String, Object>> sendWarnStats(Map<String, Object> map);

	/**
	 * 基础渠道信息
	 * @Title: basicChannel
	 * @param @param map
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	 List<Map<String, Object>> basicChannel(Map<String, Object> map);

	/**
	 * 渠道统计
	 * @author lxv
	 * @Title: channelStatistics
	 * @param @param map
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	 List<Map<String, Object>> channelStatistics(Map<String, Object> map);

	/**
	 * 每小时预警
	 * @param map
	 * @return
	 */
	 List<Map<String, Object>> hourWarn(Map<String, Object> map);

	/**
	 * 根据灾种统计
	 * @param map
	 * @return
	 */
	 List<Map<String,Object>> disasterSts(Map<String,Object> map);

	/**
	 * 查询所有渠道信息
	 * @Title: findChannelAll
	 * @param @param map
	 * @return List<Channel>    返回类型
	 */
	 List<Channel> findChannelAll(Map<String, Object> map);

	/**
	 * 查询发布的预警
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getWarnData(Map<String,Object> map);

	/**
	 * 获取地区树
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> areaTree(Map<String,Object> map);
	/**
	 *  获取灾种树
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> disasterTree(Map<String,Object> map);
	/**
	 * 根据ID和地区ID获取发布的渠道
	 * @param map
	 * @return
	 */
    List<Map<String,Object>> channelsByIdArea(Map<String,Object> map);

}
