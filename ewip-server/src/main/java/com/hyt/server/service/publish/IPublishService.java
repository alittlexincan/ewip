package com.hyt.server.service.publish;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service("publishService")
@FeignClient(name = "EWIP-PUBLISH")
public interface IPublishService {

    /**
     * 预警调用分发平台接口
     * @param map
     */
    @PostMapping("/publish/")
    JSONObject publish(@RequestBody Map<String, Object> map);


    /**
     * 预警调用分发平台邮件发送接口
     * @param json
     */
    @PostMapping("/email/send")
    JSONObject sendEmail(@RequestBody JSONObject json);



}
