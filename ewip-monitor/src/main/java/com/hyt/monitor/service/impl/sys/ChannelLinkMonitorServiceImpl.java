package com.hyt.monitor.service.impl.sys;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.hyt.monitor.config.common.utils.PinyinUtils;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.monitor.service.sys.IChannelLinkMonitorService;
import com.hyt.monitor.mapper.sys.IChannelMonMapper;


@Service("IChannelLinkMonitorService")
public class ChannelLinkMonitorServiceImpl implements IChannelLinkMonitorService {

	@Resource
	private IChannelMonMapper channelMonMapper;

	/**
	 * 获取渠道链路监控信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject getChannelLinkMonitor() {

		JSONObject json = new JSONObject();
		// 获取最新预警信息
		Map<String, Object> info = this.channelMonMapper.getNewSendWarnInfo();
		// 获取现有渠道状态
		List<Map<String, Object>> channels = this.channelMonMapper.getChannelLinkState();
		
		JSONArray left = new JSONArray();
		JSONArray right = new JSONArray();
		JSONArray bottom = new JSONArray();
		
		if(info!=null ){
			// 获取预警信息发布渠道状态
			List<Map<String, Object>> status = this.channelMonMapper.getNewSendWarnToChannel(info);

			// 组装预警信息
			json.put("message", info.get("sendContent").toString());
			json.put("img", info.get("img").toString());

			/**
			 * * @return	channelId	渠道id
			 * @return		channelName	渠道名称
			 * @return		sendMethod	手段名称
			 * @return		deployType	是否部署：1：表示部署；2：表示未部署
			 * @return		resultType	渠道状态是否畅通：1：畅通；2：不通
			 */
			for(int i = 0; i<channels.size(); i++){
				Map<String, Object> map = channels.get(i);
				int deployType = Integer.parseInt(map.get("deployType").toString());
				int resultType = Integer.parseInt(map.get("resultType").toString());
				String channelId = map.get("channelId").toString();
				String channelName = map.get("channelName").toString();
				String sendMethod = map.get("sendMethod").toString();
				JSONObject channel = status(deployType, resultType, channelName,sendMethod);
				
				for(Map<String, Object> st : status){
					if(st.get("channelId").toString() .equals(channelId) ){
						channel.put("sending", setSending(Integer.parseInt(st.get("result").toString())));
					}
				}
				if(i < 5){
					left.add(channel);
				}else if(i < 10){
					right.add(channel);
				}else if(i < 15){
					bottom.add(channel);
				}
			}
		} else {
			if (channels != null && !channels.isEmpty()) {
				for(int i = 0; i<channels.size(); i++){
					Map<String, Object> map = channels.get(i);
					int deployType = Integer.parseInt(map.get("deployType").toString());
					int resultType = Integer.parseInt(map.get("resultType").toString());
					String channelName = map.get("channelName").toString();
					String sendMethod = map.get("sendMethod").toString();
					JSONObject channel = status(deployType, resultType, channelName,sendMethod);					
					channel.put("sending",0);
					if(i < 5){
						left.add(channel);
					}else if(i < 10){
						right.add(channel);
					}else if(i < 15){
						bottom.add(channel);
					}
				}
			}
		}
		json.put("left", left);
		json.put("right", right);
		json.put("bottom", bottom);
		return json;
	}

	/**
	 * @param		deployType	是否部署：1：表示部署；2：表示未部署
	 * @param		resultType	渠道状态是否畅通：1：畅通；2：不通
	 * @return
	 */
	private static JSONObject status(int deployType, int resultType, String channelName,String sendMethod){
		JSONObject json = new JSONObject();
		json.put("text", sendMethod);
		String pinyin = PinyinUtils.getPingYin(channelName).toLowerCase();
		if(deployType == 2){
			json.put("value", 2);// 对应前端未部署
			json.put("imgSrc", "map/images/channel-link/"+pinyin+"_gray.png");
			return json;
		}
		if(deployType == -1){
			json.put("value", 1);// 对应前端异常
			json.put("imgSrc", "map/images/channel-link/"+pinyin+"_red.png");
			return json;
		}
		if(deployType == 1 && (resultType == 2 || resultType == -1)){
			json.put("value", 1);// 对应前端异常
			json.put("imgSrc", "map/images/channel-link/"+pinyin+"_red.png");
			return json;
		}
		if(deployType == 1 && resultType == 1){
			json.put("value", 0);// 对应前端正常
			json.put("imgSrc", "map/images/channel-link/"+pinyin+"_green.png");
		}
		return json;
	}
	
	/**
	 * 发送渠道状态转换
	 * @param result
	 * @return
	 */
	private static int setSending(int result){
		if(result==1){
			return 0;
		}
		return 1;
	}

}
