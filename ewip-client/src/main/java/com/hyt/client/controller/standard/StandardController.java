package com.hyt.client.controller.standard;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.controller.common.BaseController;
import com.hyt.client.service.standard.IStandardService;
import com.hyt.client.utils.UploadFileUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @Author: lixiaowei
 * @Description: 规范制度控制层
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
     * 规范制度上传文件夹
     */
    @Value("${web.standard-path}")
    private String standard;

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


    @Autowired
    private IStandardService standardService;


    /**
     * 添加信息
     * @param map
     * @return
     */
    @PostMapping("/insert")
    JSONObject insert(@RequestParam Map<String,Object> map, @RequestParam("addFile") MultipartFile file){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("createUser", employee.getString("id"));
        // 文件开始上传
        String fileUrl = UploadFileUtil.uploadFile(file, uploadPath, standard);
        // 上传成功走后台添加数据
        if(fileUrl!=null) {
            // 添加文件路径数据
            map.put("path", fileUrl);
        }
        return this.standardService.insert(map);
    }

    /**
     * 修改信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    JSONObject update(@RequestParam Map<String,Object> map, @RequestParam("updateFile") MultipartFile file){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("updateUser", employee.getString("id"));
        if(!file.getOriginalFilename().equals("")) {
            // 文件开始上传
            String fileUrl = UploadFileUtil.uploadFile(file, uploadPath, standard);
            // 上传成功走后台添加数据
            if (fileUrl != null) {
                // 添加渠道数据
                map.put("path", fileUrl);
            }
        }
        return this.standardService.update(map);
    }

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id){
        return this.standardService.deleteById(id);
    }

    /**
     * 根据ids批量删除信息
     * @param id
     * @return
     */
    @PostMapping("/delete")
    JSONObject deleteBatch(@RequestParam(value = "id") String id){
        return this.standardService.deleteBatch(id);
    }

    /**
     * 根据id查询详细信息
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id){
        return this.standardService.selectById(id);
    }

    /**
     * 分页查询信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map){
        return this.standardService.selectAll(map);
    }



}
