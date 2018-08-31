package com.hyt.client.controller.warn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.warn.IWarnEditOptionService;
import com.xincan.utils.disaster.DisasterUtil;
import com.xincan.utils.disaster.MsgTypeUtil;
import com.xincan.utils.ip.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
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


    /**
     * 获取上传的文件夹，具体路径参考application.properties中的配置
     */
    @Value("${web.upload-path}")
    private String uploadPath;

    /**
     * 灾种图标上传文件夹
     */
    @Value("${web.disaster-path}")
    private String disaster;


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

    @GetMapping("/wechat")
    public JSONArray getWechatWarnInfo(HttpServletRequest request){
        JSONArray array = new JSONArray();
        try {
        InetAddress a = IPUtil.getLocalHostLANAddress();
        a.getHostAddress();

        String ip = a.getHostAddress();
        String uri = request.getScheme() + "://" + ip + ":" + request.getServerPort() + request.getContextPath();
        System.out.println(uri);


        JSONArray data = this.warnEditOptionService.getWechatWarnInfo().getJSONArray("data");
        if (data == null || data.size() > 0) {
            data.forEach(warn -> {
                JSONObject w = JSONObject.parseObject(warn.toString());
                JSONObject json = new JSONObject();
                JSONObject disaster = DisasterUtil.getDisasterInfo(w.getInteger("disasterColor"));
                json.put("eventType", w.getString("eventType")); // 灾种编码
                json.put("headline", w.getString("organizationName") + MsgTypeUtil.parseMsgType(w.getString("msgType")) + w.getString("disasterName") + disaster.getString("color") + "预警" + disaster.getString("level"));             // 发布标题
                json.put("sender", w.getString("organizationName"));    // 机构编码
                json.put("effective", w.getString("effective"));        // 发布时间
                json.put("instruction", w.getString("instruction"));    // 防御指南
                json.put("description", w.getString("description"));    // 采取措施
                json.put("img", uri + w.getString("img"));   // 预警图标
                array.add(json);
            });
        }
    }catch (Exception e){
        e.getMessage();
    }
        return array;
    }


}
