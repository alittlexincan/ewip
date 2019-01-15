package com.hyt.monitor.mapper.monitor;

import java.util.List;
import java.util.Map;

/**
 * 渠道链路监控服务接口层
 * @author JiangXincan
 *
 */
public interface IChannelMonMapper {

    /**
     * 获取渠道链路监控状态
     * @return	channelId	渠道id
     * @return	channelName	渠道名称
     * @return	deployType	是否部署：1：表示部署；2：表示未部署
     * @return	resultType	渠道状态是否畅通：1：畅通；2：不通
     * @return	map
     * @throws Exception
     */
     List<Map<String, Object>> getChannelLinkState(Map<String, Object> map) ;

    /**
     * 获取最新预警发布所推送的渠道
     * @param map
     * @return
     * @throws Exception
     */
     List<Map<String, Object>> getNewSendWarnToChannel(Map<String, Object> map) ;

    /**
     * 获取最新预警信息
     *
     * @return  icon 预警LOGO
     * @return	message 预警信息
     * @return	map
     * @throws Exception
     */
     Map<String, Object> getNewSendWarnInfo(Map<String, Object> map) ;
}
