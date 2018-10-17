package com.hyt.monitor.controller.monitor;

import com.alibaba.fastjson.JSONObject;
import com.hyt.monitor.service.monitor.IPublishStatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/publishStatistics")
public class PublishStatisticsController {
	
	@Resource
	private IPublishStatisticsService service;

	/**
	 * 预警级别统计
	 * @param map
	 * @return
	 */
	@RequestMapping("/drawWarnLevelTotal")
	@ResponseBody
	public JSONObject drawWarnLevelTotal(@RequestParam Map<String, Object> map){
		try {
			return this.service.drawWarnLevelTotal(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发布地区统计
	 * @param map
	 * @return
	 */

	@RequestMapping("/drawPublishAreaTotal")
	@ResponseBody
	public JSONObject drawPublishAreaTotal(@RequestParam Map<String, Object> map) {
		try {
			return this.service.drawPublishAreaTotal(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询发布预警信息
	 * @param map
	 * @return
	 */
	@RequestMapping("/warnList")
	@ResponseBody
	public JSONObject warnList(@RequestParam Map<String, Object> map){
		try {
			return this.service.warnList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 已发布预警信息
	 * @param map
	 * @return
	 */

	@RequestMapping("/alreadyPub")
	@ResponseBody
	public JSONObject alreadyPub(@RequestParam Map<String, Object> map){
		try {
			return this.service.alreadyPub(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 渠道统计
	 * @param map
	 * @return
	 */

	@RequestMapping("/drawPublishChannelTotal")
	@ResponseBody
	public JSONObject drawPublishChannelTotal(@RequestParam Map<String, Object> map){
		try {
			return this.service.drawPublishChannelTotal(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据小时统计
	 * @param map
	 * @return
	 */
	@RequestMapping("/hourWarn")
	@ResponseBody
	public JSONObject hourWarn(@RequestParam Map<String, Object> map){
		try {
			return this.service.hourWarn(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 责任人受众统计
	 * @param map
	 * @return
	 */

	@RequestMapping("/personStas")
	@ResponseBody
	public JSONObject personStas(@RequestParam Map<String, Object> map){
		try {
			return this.service.personStas(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
