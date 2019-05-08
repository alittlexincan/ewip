package com.hyt.client.controller.warn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.controller.common.BaseController;
import com.hyt.client.service.warn.IWarnEditService;
import com.hyt.client.utils.UploadFileUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
 * @Description: 预警配置信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@SuppressWarnings("unchecked")
@RestController
@RequestMapping("warn/edit")
public class WarnEditController extends BaseController {

    /**
     * 获取上传的文件夹，具体路径参考application.properties中的配置
     */
    @Value("${web.upload-path}")
    private String uploadPath;


    @Value("${yitihua-uploadPath}")
    private String yiTiHuaPath;

    /**
     * 灾种图标上传文件夹
     */
    @Value("${web.warn-file-path}")
    private String warnFile;

    /**
     * 注入预警编辑业务访问接口
     */
    @Autowired
    private IWarnEditService warnEditService;

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
        JSONArray file = UploadFileUtil.upload(files, uploadPath, warnFile);

        JSONArray yiTiHua = UploadFileUtil.upload(files, yiTiHuaPath, "");

        map.put("files", file != null ? file.toJSONString() : "");
        JSONObject json = new JSONObject(map);
        System.out.println(map);
        System.out.println(json);
        return this.warnEditService.insert(map);
    }

    /**
     * 添加预警配置信息
     * @param map
     * @return
     */
    @PostMapping("/resend")
    public JSONObject resend(HttpSession session, @RequestParam Map<String,Object> map, @RequestParam("warnFile") MultipartFile[] files){
        Map<String, Object> employee = (Map<String, Object>) session.getAttribute("employee");
        map.put("employeeId",employee.get("id"));
        map.put("employeeName",employee.get("name"));
        map.put("areaId",employee.get("areaId"));
        map.put("areaName",employee.get("areaName"));
        map.put("organizationId",employee.get("organizationId"));
        map.put("organizationName",employee.get("organizationName"));
        if(files.length > 0){
            // 文件开始上传
            JSONArray file = UploadFileUtil.upload(files, uploadPath, warnFile);
            map.put("files", file != null ? file.toJSONString() : "");
        }
        return this.warnEditService.resend(map);
    }

    /**
     * 分页查询预警发布信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    JSONObject select(HttpSession session, @RequestParam Map<String,Object> map){

        Map<String, Object> employee = (Map<String, Object>) session.getAttribute("employee");
//        map.put("employeeId",employee.get("id"));
//        map.put("employeeName",employee.get("name"));
        map.put("areaId",employee.get("areaId"));
        map.put("areaName",employee.get("areaName"));
        map.put("organizationId",employee.get("organizationId"));
        map.put("organizationName",employee.get("organizationName"));
        map.put("organizationType",employee.get("organizationType"));

        return this.warnEditService.selectAll(map);
    }

    /**
     * 文件下载
     * @param map
     * @return
     */
    @GetMapping("/download")
    public void download(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        File file = new File( this.uploadPath + "/" + map.get("url").toString());
        downloadFile(file, request, response);
    }


    /**
     * 根据预警条件获取预警信息
     * @param map
     * @return
     */
    @GetMapping("/info")
    public JSONObject getHomeWarnInfo(@RequestParam Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("areaId", employee.getString("areaId"));
//        map.put("organizationId", employee.getString("organizationId"));
        return this.warnEditService.selectWarnInfo(map);
    }


    /**
     * 终止预警
     * @param map
     * @return
     * @return
     */
    @PostMapping("/stopWarn")
    public JSONObject stopWarn(@RequestParam Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("employeeId",employee.get("id"));
        map.put("employeeName",employee.get("name"));
        map.put("organizationId",employee.get("organizationId"));
        map.put("organizationName",employee.get("organizationName"));
        map.put("advice","预警终止");
        return this.warnEditService.stopWarn(map);
    }
}
