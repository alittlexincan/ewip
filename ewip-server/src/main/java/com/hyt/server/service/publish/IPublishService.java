package com.hyt.server.service.publish;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service("publishService")
@FeignClient("EWIP-PUBLISH")
public interface IPublishService {

    /**
     * 预警调用分发平台接口
     * @param map
     */
    @PostMapping("/publish/")
    void publish(@RequestBody Map<String, Object> map);


    /**
     * 一键式发布调用分发平台接口
     * @param map
     */
    @PostMapping("/publish/sms")
    void sms(@RequestBody Map<String, Object> map);
}
