package com.hyt.publish.service.wechat;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: JiangXincan
 * @Description: 发布渠道：微信接口层
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
public interface IWechatService {

    JSONObject wechat(JSONObject json);

}
