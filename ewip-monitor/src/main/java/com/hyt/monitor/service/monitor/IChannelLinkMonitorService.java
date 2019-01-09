package com.hyt.monitor.service.monitor;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 渠道链路监控服务接口层
 * @author lixiaowei
 *
 */
public interface IChannelLinkMonitorService {

	/**
	 * 获取渠道链路监控信息
	 * @return
	 * @throws Exception
	 */
	JSONObject getChannelLinkMonitor(Map<String, Object> map) ;
}
