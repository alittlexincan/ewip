package com.hyt.monitor.controller.monitor;

import com.alibaba.fastjson.JSONObject;
import com.hyt.monitor.service.monitor.SystemMonitorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 网络监控控制层
 * @author lixiaowei
 *
 */
@Controller
@RequestMapping("systemMonitor")
public class SystemMonitorController {
	
	@Resource
	private SystemMonitorService service;

	/**
	 * 网络监控-系统运行监控
	 * @param map
	 * @return
	 */
	@RequestMapping("/systemMonitoring")
	@ResponseBody
	public JSONObject systemMonitoring(@RequestParam Map<String, Object> map){
		try {
			return this.service.systemMonitoring(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 网络监控-终端监控
	 * @param map
	 * @return
	 */
	@RequestMapping("/warnTerminalMonitor")
	@ResponseBody
	public JSONObject warnTerminalMonitor(@RequestParam Map<String, Object> map){
		try {
			return this.service.warnTerminalMonitor(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
