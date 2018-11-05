package com.hyt.client.controller.ueditor;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.ueditor.IUeditorService;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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
     * 导出word
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
        content = content.replace("/client", "D:/ewip-file");
        System.out.println(content);
        SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMddHHmmss");
        String title = map.get("title") + "_" + sdf.format(new Date());
        try {

            byte b[] = content.getBytes("GBK");  //这里是必须要设置编码的，不然导出中文就会乱码。
            ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中

            /*
             * 关键地方
             * 生成word格式 */
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
            ueditorService.insert(newMap);//插入数据库表
            ueditorService.sendMail(newMap);//发送邮件
            json.put("code","success");
            return json;

            //输出文件
//            request.setCharacterEncoding("utf-8");
//            response.setContentType("application/msword");//导出word格式
//            response.addHeader("Content-Disposition", "attachment;filename=" +
//                    new String(title.getBytes("GB2312"),"iso8859-1") + ".doc");
//            ServletOutputStream ostream = response.getOutputStream();
//            poifs.writeFilesystem(ostream);
//            bais.close();
//            ostream.close();

        }catch(Exception e){
            e.printStackTrace();
            json.put("code","error");
            return json;
        }
    };

    public static void main(String[] args) {
        String uuidParent = UUID.randomUUID().toString().replace("-", "");

        String uuidChild = UUID.randomUUID().toString().replace("-", "");

        System.out.println(uuidParent);
        System.out.println(uuidChild);
    }

}
