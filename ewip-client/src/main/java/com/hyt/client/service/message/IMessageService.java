package com.hyt.client.service.message;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 一键发布信息接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("messageService")
@FeignClient("EWIP-SERVER")
public interface IMessageService {

    /**
     * 添加预警编辑信息
     * @param map
     * @return
     */
    @PostMapping("/message/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);

}
