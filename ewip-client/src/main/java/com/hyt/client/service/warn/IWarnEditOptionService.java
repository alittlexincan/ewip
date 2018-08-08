package com.hyt.client.service.warn;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 预警编辑操作信息接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("warnEditOptionService")
@FeignClient("EWIP-SERVER")
public interface IWarnEditOptionService {

    /**
     * 根据条件分页查询预警编辑流程信息
     * @param map
     * @return
     */
    @GetMapping("/warn/option/select/flow")
    JSONObject selectFlowByParam(@RequestParam Map<String, Object> map);

    /**
     * 添加预警流程信息
     * @param map
     * @return
     */
    @PostMapping("/warn/option/insert/flow")
    JSONObject insert(@RequestParam Map<String, Object> map);

    /**
     * 修改预警状态
     * @param map
     * @return
     */
    @PostMapping("/warn/option/update/status")
    JSONObject updateStatus(@RequestParam Map<String, Object> map);

}
