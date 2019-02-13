package com.hyt.client.service.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

/**
 * @Author: 李晓伟
 * @Description: 渠道手段管理接口层
 * @Modified By:
 */
@Service("channelConfigService")
@FeignClient("EWIP-SERVER")
public interface IChannelConfigService {


    /**
     * 分页查询信息
     * @param map
     * @return
     */
    @GetMapping("/channel/config/select")
    JSONObject selectAll(@RequestParam Map<String, Object> map);

    /**
     * 添加信息
     * @param map
     * @return
     */
    @PostMapping("/channel/config/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);


    /**
     * 查询信息
     * @param map
     * @return
     */
    @GetMapping("/channel/config/select/type")
    JSONObject selectByType(@RequestParam Map<String, Object> map);

}
