package com.hyt.monitor.controller.sys;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.hyt.monitor.entity.sys.Channel;
import com.hyt.monitor.entity.sys.Tree;
import com.hyt.monitor.entity.sys.ZTree;
import com.hyt.monitor.service.sys.IEventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping("/event")
public class EventController {
	
	@Resource
	private IEventService service;

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
	public JSONObject hourWarn(@RequestParam Map<String, Object> map)throws Exception{
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

	/**
	 *
	 * channelList(渠道信息)
	 * @Title: channelList
	 * @param @param map
	 * @param @return    设定文件
	 */
	@RequestMapping(value = "/channelList")
	@ResponseBody
	public List<Channel> channelList(@RequestParam Map<String, Object> map) {
		try {
			return this.service.findChannelAll(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取灾种树
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/disasterTree")
	@ResponseBody
	public Object disasterTree(@RequestParam Map<String, Object> map) {
		try {
			List<Tree> disasterTree = this.service.disasterTree(map);
			return disasterTree;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取地区树
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/areaTree")
	@ResponseBody
	public Object areaTree(@RequestParam Map<String, Object> map) {
		try {
			List<Tree> areaTree = this.service.areaTree(map);
			return areaTree;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 获取发布预警
	 * @param map
	 * @return
	 */
	@RequestMapping("/getWarnData")
	@ResponseBody
	public JSONArray getWarnData(@RequestParam Map<String, Object> map){
		try {
			return this.service.getWarnData(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取预警发布的渠道
	 * @param map
	 * @return
	 */
	@RequestMapping("/channelsByIdArea")
	@ResponseBody
	public JSONObject channelsByIdArea(@RequestParam Map<String, Object> map){
		try {
			return this.service.channelsByIdArea(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
