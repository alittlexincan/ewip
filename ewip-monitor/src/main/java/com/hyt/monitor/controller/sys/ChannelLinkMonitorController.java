package com.hyt.monitor.controller.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import com.hyt.monitor.service.sys.IChannelLinkMonitorService;

/**
 * 渠道链路监控控制层
 * @author lixiaowei
 *
 */
@Controller
@RequestMapping("channel")
public class ChannelLinkMonitorController {
	
	@Resource
	private IChannelLinkMonitorService service;
	
	/**
	 * 获取渠道链路监控信息
	 * @return
	 */
	@RequestMapping("monitor")
	@ResponseBody
	public JSONObject getChannelLinkMonitor(){
		try {
			return this.service.getChannelLinkMonitor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
