package com.hyt.client.service.warn;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 地区信息接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("warnEditService")
@FeignClient("EWIP-SERVER")
public interface IWarnEditService {

    /**
     * 添加预警编辑信息
     * @param map
     * @return
     */
    @PostMapping("/warn/edit/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);

    /**
     * 重发预警编辑信息
     * @param map
     * @return
     */
    @PostMapping("/warn/edit/resend")
    JSONObject resend(@RequestParam Map<String, Object> map);

    /**
     * 分页查询预警发布信息
     * @param map
     * @return
     */
    @GetMapping("/warn/edit/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map);


    /**
     * 根据预警条件获取预警信息
     * @return
     */
    @GetMapping("/warn/edit/info")
    JSONObject selectWarnInfo(@RequestParam Map<String,Object> map);

}
