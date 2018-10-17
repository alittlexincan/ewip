package com.hyt.monitor.service.sys;

import com.alibaba.fastjson.JSONObject;

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
	JSONObject getChannelLinkMonitor() ;
}
