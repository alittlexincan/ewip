package com.hyt.client.controller.standard;


import com.alibaba.fastjson.JSONObject;
import com.hyt.client.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 一键发布信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("standard")
public class StandardController extends BaseController {


    /**
     * 获取上传的文件夹，具体路径参考application.properties中的配置
     */
    @Value("${web.upload-path}")
    private String uploadPath;
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

}
