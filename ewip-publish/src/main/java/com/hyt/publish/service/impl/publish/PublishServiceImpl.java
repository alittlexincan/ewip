package com.hyt.publish.service.impl.publish;

import com.alibaba.fastjson.JSONObject;
import com.hyt.publish.service.publish.IPublishService;
import org.springframework.stereotype.Service;

/**
 * @Author: JiangXincan
 * @Description: 信息发布接口实现层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Service("publishService")
public class PublishServiceImpl implements IPublishService {

    /**
     * 信息发布
     * @param json
     */
    @Override
    public void publish(JSONObject json) {
        System.out.println(json);
    }
}
