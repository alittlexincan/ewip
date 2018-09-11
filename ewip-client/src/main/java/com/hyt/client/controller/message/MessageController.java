package com.hyt.client.controller.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.message.IMessageService;
import com.hyt.client.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 一键发布信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("message")
public class MessageController {

    /**
     * 获取上传的文件夹，具体路径参考application.properties中的配置
     */
    @Value("${web.upload-path}")
    private String uploadPath;

    /**
     * 灾种图标上传文件夹
     */
    @Value("${web.message-file-path}")
    private String messageFile;

    @Autowired
    private IMessageService messageService;
    /**
     * 添加预警配置信息
     * @param map
     * @return
     */
    @PostMapping("/insert")
    public JSONObject insert(HttpSession session, @RequestParam Map<String,Object> map, @RequestParam("warnFile") MultipartFile[] files){
        Map<String, Object> employee = (Map<String, Object>) session.getAttribute("employee");
        map.put("employeeId",employee.get("id"));
        map.put("employeeName",employee.get("name"));
        map.put("areaId",employee.get("areaId"));
        map.put("areaName",employee.get("areaName"));
        map.put("organizationId",employee.get("organizationId"));
        map.put("organizationName",employee.get("organizationName"));
        // 文件开始上传
        JSONArray file = UploadFileUtil.upload(files, uploadPath, messageFile);
        map.put("files", file != null ? file.toJSONString() : "");
        return this.messageService.insert(map);
    }
}
