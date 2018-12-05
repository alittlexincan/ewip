package com.hyt.client.controller.ueditor;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.ueditor.IUeditorService;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("ueditor")
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
            newMap.put("group",map.get("group").toString());
            newMap.put("type",map.get("type").toString());
            newMap.put("title",map.get("title").toString());
            newMap.put("path","/"+serverFile+"/"+ title+".doc");
            newMap.put("filePath",uploadPath+"/"+serverFile+"/"+ title+".doc");

            Subject subject = SecurityUtils.getSubject();
            JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
            newMap.put("areaId", employee.getString("areaId"));
            newMap.put("organizationId", employee.getString("organizationId"));

            ueditorService.insert(newMap);      //插入数据库表
            ueditorService.sendMail(newMap);    //发送邮件
            json.put("code","success");
            return json;
        }catch(Exception e){
            e.printStackTrace();
            json.put("code","error");
            return json;
        }
    };

}
