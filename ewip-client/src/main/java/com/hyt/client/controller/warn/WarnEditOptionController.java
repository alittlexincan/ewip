package com.hyt.client.controller.warn;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.warn.IWarnEditOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 预警操作信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("warn/option")
public class WarnEditOptionController {

    @Autowired
    private IWarnEditOptionService warnEditOptionService;

    /**
     * 根据条件分页查询预警编辑流程信息
     * @param map
     * @return
     */
    @GetMapping("/select/flow")
    JSONObject selectFlowByParam(@RequestParam Map<String,Object> map){
        return this.warnEditOptionService.selectFlowByParam(map);
    }

    /**
     * 添加预警流程信息
     * @param map
     * @return
     */
    @PostMapping("/insert/flow")
    JSONObject insert(HttpSession session, @RequestParam Map<String, Object> map){
        Map<String, Object> employee = (Map<String, Object>) session.getAttribute("employee");
        map.put("employeeId",employee.get("id"));
        map.put("employeeName",employee.get("name"));
        map.put("organizationId",employee.get("organizationId"));
        map.put("organizationName",employee.get("organizationName"));
        map.put("organizationCode",employee.get("organizationCode"));
        return this.warnEditOptionService.insert(map);
    }

    /**
     * 修改预警状态
     * @param map
     * @return
     */
    @PostMapping("/update/status")
    JSONObject updateStatus(@RequestParam Map<String,Object> map){
        return this.warnEditOptionService.updateStatus(map);
    }

}
