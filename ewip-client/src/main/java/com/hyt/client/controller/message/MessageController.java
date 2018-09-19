package com.hyt.client.controller.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.controller.common.BaseController;
import com.hyt.client.service.message.IMessageService;
import com.hyt.client.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 一键发布信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("message")
public class MessageController extends BaseController {

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
     * 分页查询地区信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map){
        return this.messageService.selectAll(map);
    }



    /**
     * 添加一键发布信息
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

    /**
     * 查询一键发布详情
     * @param map
     * @return
     */
    @PostMapping("/detail")
    public JSONObject detail(@RequestParam Map<String, Object> map){
        return this.messageService.detail(map);
    }

    /**
     * 文件下载
     * @param map
     * @return
     */
    @GetMapping("/download")
    public void download(@RequestParam Map<String, Object> map,HttpServletRequest request, HttpServletResponse response){
        File file = new File( this.uploadPath + "/" + map.get("url").toString());
        downloadFile(file, request, response);
    }
}
