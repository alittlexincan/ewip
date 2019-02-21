package com.zxyt.ocpp.client.controller.warn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxyt.ocpp.client.config.common.result.ResultObject;
import com.zxyt.ocpp.client.config.common.result.ResultResponse;
import com.zxyt.ocpp.client.service.publish.IPublishService;
import com.zxyt.ocpp.client.service.warn.IWarnEditService;
import com.zxyt.ocpp.client.utils.UploadFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(tags = {"预警发布管理"}, description = "WarnEditController")
@RestController
@RequestMapping("/warn/edit")
public class WarnEditController {

    /**
     * 获取上传的文件夹，具体路径参考application.properties中的配置
     */
    @Value("${web.upload-path}")
    private String uploadPath;

    /**
     * 灾种图标上传文件夹
     */
    @Value("${web.warn-file-path}")
    private String warnFile;

    @Autowired
    private IWarnEditService warnEditService;

    @Autowired
    private IPublishService publishService;

    @PostMapping("/insert")
    public ResultObject<Object> insert(HttpSession session, @ApiParam(hidden = true) @RequestParam Map<String,Object> map, @RequestParam("warnFile") MultipartFile[] files){
        Map<String, Object> employee = (Map<String, Object>) session.getAttribute("employee");
        map.put("employeeId",employee.get("id"));
        map.put("employeeName",employee.get("name"));
        map.put("areaId",employee.get("areaId"));
        map.put("areaName",employee.get("areaName"));
        map.put("organizationId",employee.get("organizationId"));
        map.put("organizationName",employee.get("organizationName"));
        // 文件开始上传
        JSONArray file = UploadFileUtil.upload(files, uploadPath, warnFile);
        map.put("files", file != null ? file.toJSONString() : "");

        JSONObject json = new JSONObject(map);

        JSONObject jsonObject = this.warnEditService.insert(json);
        if(jsonObject.getInteger("code") == 200){
            // 调用分发接口
            this.publishService.publish(jsonObject);
            return ResultResponse.make(200,"发布预警信息成功",json);
        }
        return ResultResponse.make(500,"发布预警信息失败",null);
    }
}
