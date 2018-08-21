package com.hyt.publish.service.publish;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: JiangXincan
 * @Description: 信息发布接口
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
public interface IPublishService {

    void publish(JSONObject json);
}
