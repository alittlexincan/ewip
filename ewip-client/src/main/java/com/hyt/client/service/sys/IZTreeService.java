package com.hyt.client.service.sys;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * zTree树接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("zTreeService")
@FeignClient("EWIP-SERVER")
public interface IZTreeService {

    /**
     * 分页查询地区信息
     * @param map
     * @return
     */
    @PostMapping("/tree/area")
    JSONObject getAreaTree(@RequestParam Map<String, Object> map);

}
