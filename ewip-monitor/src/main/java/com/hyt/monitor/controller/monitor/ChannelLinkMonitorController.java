package com.hyt.monitor.controller.monitor;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import com.hyt.monitor.service.monitor.IChannelLinkMonitorService;

import java.util.Map;

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
	public JSONObject getChannelLinkMonitor(@RequestParam Map<String, Object> map){
		try {
			String areaCode=map.get("areaCode").toString();
			if(map.get("level").equals("1")){
				areaCode=areaCode.substring(0,2);
			}else if(map.get("level").equals("2")){
				areaCode=areaCode.substring(0,4);
			}else if(map.get("level").equals("3")){
				areaCode=areaCode.substring(0,6);
			}
			return this.service.getChannelLinkMonitor(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
