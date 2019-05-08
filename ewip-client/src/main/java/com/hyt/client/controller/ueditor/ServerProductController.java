package com.hyt.client.controller.ueditor;


import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.ueditor.IServerProductService;
import com.sun.net.httpserver.Authenticator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@RestController
@RequestMapping("serverProduct")
public class ServerProductController {
    /**
     * 获取上传的文件夹，具体路径参考application.properties中的配置
     */
    @Value("${web.upload-path}")
    private String uploadPath;

    @Autowired
    private IServerProductService serverProductService;
    /**
     * 分页查询信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("empAreaId", employee.getString("areaId"));
        String areaCode=employee.getString("areaCode");
        if(employee.getString("level").equals("1")){
            areaCode=areaCode.substring(0,2);
        }else if(employee.getString("level").equals("2")){
            areaCode=areaCode.substring(0,4);
        }else if(employee.getString("level").equals("3")){
            areaCode=areaCode.substring(0,6);
        }
        map.put("areaCode", areaCode);
        return this.serverProductService.selectAll(map);
    }

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id){
        return this.serverProductService.deleteById(id);
    }


    /**
     * 下载文件
     * @return
     */
    @GetMapping("/downFile")
    public void downFile(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> map){
        String path= null;
        try {
            path = URLDecoder.decode(map.get("path").toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File file=new File(uploadPath+path);
        if(!file.exists()){
            return ;
        }
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String fileName = file.getName();
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; fileName="+  fileName +";filename*=utf-8''"+URLEncoder.encode(fileName,"UTF-8"));
            byte[] buff = new byte[1024];
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查询列表
     * @param map
     * @return
     */
    @GetMapping("/selectList")
    JSONObject selectList(@RequestParam Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("empAreaId", employee.getString("areaId"));
        String areaCode=employee.getString("areaCode");
        if(employee.getString("level").equals("1")){
            areaCode=areaCode.substring(0,2);
        }else if(employee.getString("level").equals("2")){
            areaCode=areaCode.substring(0,4);
        }else if(employee.getString("level").equals("3")){
            areaCode=areaCode.substring(0,6);
        }
        map.put("areaCode", areaCode);
        return this.serverProductService.selectList(map);
    }




}
