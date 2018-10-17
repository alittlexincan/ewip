package com.hyt.monitor.mapper.sys;

import java.util.List;
import java.util.Map;

public interface PublishStatisticsMapper {

	/**
	 * 基础颜色数据
	 * @author lxv
	 * @Title: basicColor
	 * @param @param map
	 */
	 List<Map<String, Object>> basicColor(Map<String, Object> map);
	/**
	 * 预警级别统计
	 * @author lxv
	 * @Title: drawWarnLevelTotal
	 * @param @param map
	 * @param @return    设定文件
	 */
	 List<Map<String, Object>> drawWarnLevelTotal(Map<String, Object> map);

	/**
	 * 发布地区统计
	 * @author lxv
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
	 * @author lxv
	 * @Title: sendWarnStats
	 * @param @param map
	 * @param @return    设定文件
	 * @return List<Object>    返回类型
	 * @throws
	 */
	 List<Map<String, Object>> sendWarnStats(Map<String, Object> map);

	/**
	 * 基础渠道信息
	 * @author lxv
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
	 * 发送渠道对应用户
	 * @author lxv
	 * @Title: taskIdList
	 * @param @param map
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	 List<Map<String, Object>> channelUserList(Map<String, Object> map);

	/**
	 * 任务ID列表
	 * @author lxv
	 * @Title: taskIdList
	 * @param @param map
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	List<Map<String, Object>> taskIdList(Map<String, Object> map);

	/**
	 * 获取责任人以及用户
	 * @author lxv
	 * @Title: personNumList
	 * @param @param map
	 * @param @return    设定文件
	 * @return List<Map<String,Object>>    返回类型
	 * @throws
	 */
	 List<Map<String, Object>> personNumList(Map<String, Object> map);
}
