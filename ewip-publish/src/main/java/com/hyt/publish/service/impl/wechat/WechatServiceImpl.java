package com.hyt.publish.service.impl.wechat;

import com.alibaba.fastjson.JSONObject;
import com.hyt.publish.service.wechat.IWechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: JiangXincan
 * @Description: 发布渠道：微信接口实现层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Slf4j
@Service("wechatService")
public class WechatServiceImpl implements IWechatService {

    @Override
    public void wechat(JSONObject json) {
        log.info("===============微信推送=====================");
    }
}
