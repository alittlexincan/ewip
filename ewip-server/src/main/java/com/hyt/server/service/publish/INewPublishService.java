package com.hyt.server.service.publish;

import com.zhxu.model.message.PubInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName IPublishService
 * @Author Xincan
 * @Version 1.0
 **/
@Service("newPublishService")
@FeignClient(name = "MESSAGE-PUBLISHER")
public interface INewPublishService {

    /**
     * 预警调用分发平台接口
     * @param json
     */
    @PostMapping("/publish/")
    void publish(@RequestBody PubInfo json);
}
