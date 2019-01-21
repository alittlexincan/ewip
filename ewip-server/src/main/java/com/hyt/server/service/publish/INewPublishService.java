package com.hyt.server.service.publish;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service("newPublishService")
@FeignClient(name = "MESSAGE-CENTER")
public interface INewPublishService {

    /**
     * 预警调用分发平台接口
     */
    @PostMapping("/publish/")
    void publish(@RequestBody JSONObject object);
}
