package com.zxyt.ocpp.client.controller.ueditor;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zxyt.ocpp.client.config.common.result.ResultObject;
import com.zxyt.ocpp.client.config.common.result.ResultResponse;
import com.zxyt.ocpp.client.entity.ueditor.Ueditor;
import com.zxyt.ocpp.client.service.ueditor.IUeditorService;
import io.swagger.annotations.ApiParam;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: msm
 * @Description: 产品信息
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@RestController
@RequestMapping("/ueditor")
public class UeditorController {

    /**
     * 获取上传的文件夹，具体路径参考application.properties中的配置
     */
    @Value("${web.upload-path}")
    private String uploadPath;

    /**
     * 灾种图标上传文件夹
     */
    @Value("${web.server-file-path}")
    private String serverFile;

    @Autowired
    private IUeditorService ueditorService;

    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.ueditorService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除地区成功");
        }
        return ResultResponse.make(500,"删除地区失败");
    }

    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<Ueditor> pageInfo = this.ueditorService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 制作word
     * @return
     */
    @PostMapping("/getWord")
    public JSONObject getWord(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String,Object> map){
        Map<String,Object> newMap=new HashMap<>();
        JSONObject json=new JSONObject();
        File fileDir = new File(uploadPath+"/"+serverFile);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        String content=map.get("html").toString();
        SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMddHHmmss");
        String title = map.get("title") + "_" + sdf.format(new Date());
        try {
            byte b[] = content.getBytes("GBK");  //这里是必须要设置编码的，不然导出中文就会乱码。
            ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中
            // 生成word格式
            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
            FileOutputStream ostream = new FileOutputStream(uploadPath+"/"+serverFile+"/"+ title+".doc");
            poifs.writeFilesystem(ostream);
            bais.close();
            ostream.close();
            newMap.put("type",map.get("type").toString());
            newMap.put("title",map.get("title").toString());
            newMap.put("path","/"+serverFile+"/"+ title+".doc");
            newMap.put("filePath",uploadPath+"/"+serverFile+"/"+ title+".doc");

            Subject subject = SecurityUtils.getSubject();
            JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
            newMap.put("areaId", employee.getString("areaId"));
            newMap.put("organizationId", employee.getString("organizationId"));
            newMap.put("createTime", new Date());
            ueditorService.insert(newMap);      //插入数据库表
            json.put("code","success");
            return json;
        }catch(Exception e){
            e.printStackTrace();
            json.put("code","error");
            return json;
        }
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
            response.setHeader("Content-Disposition", "attachment; fileName="+  fileName +";filename*=utf-8''"+ URLEncoder.encode(fileName,"UTF-8"));
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



}




