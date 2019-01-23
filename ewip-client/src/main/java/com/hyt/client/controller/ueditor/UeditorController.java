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
        System.out.println(map);
        String content=map.get("html").toString();
        System.out.println(content);
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
//            newMap.put("group",map.get("group").toString());
            newMap.put("type",map.get("type").toString());
            newMap.put("title",map.get("title").toString());
            newMap.put("path","/"+serverFile+"/"+ title+".doc");
            newMap.put("filePath",uploadPath+"/"+serverFile+"/"+ title+".doc");

            Subject subject = SecurityUtils.getSubject();
            JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
            newMap.put("areaId", employee.getString("areaId"));
            newMap.put("organizationId", employee.getString("organizationId"));
            ueditorService.insert(newMap);      //插入数据库表
//            ueditorService.sendMail(newMap);    //发送邮件
            json.put("code","success");
            return json;
        }catch(Exception e){
            e.printStackTrace();
            json.put("code","error");
            return json;
        }
    }



//    String str = " <!--[if gte mso 9]><xml><w:WordDocument><w:View>Print</w:View><w:TrackMoves>false</w:TrackMoves><w:TrackFormatting/><w:ValidateAgainstSchemas/><w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid><w:IgnoreMixedContent>false</w:IgnoreMixedContent><w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText><w:DoNotPromoteQF/><w:LidThemeOther>EN-US</w:LidThemeOther><w:LidThemeAsian>ZH-CN</w:LidThemeAsian><w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript><w:Compatibility><w:BreakWrappedTables/><w:SnapToGridInCell/><w:WrapTextWithPunct/><w:UseAsianBreakRules/><w:DontGrowAutofit/><w:SplitPgBreakAndParaMark/><w:DontVertAlignCellWithSp/><w:DontBreakConstrainedForcedTables/><w:DontVertAlignInTxbx/><w:Word11KerningPairs/><w:CachedColBalance/><w:UseFELayout/></w:Compatibility><w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel><m:mathPr><m:mathFont m:val='Cambria Math'/><m:brkBin m:val='before'/><m:brkBinSub m:val='--'/><m:smallFrac m:val='off'/><m:dispDef/><m:lMargin m:val='0'/> <m:rMargin m:val='0'/><m:defJc m:val='centerGroup'/><m:wrapIndent m:val='1440'/><m:intLim m:val='subSup'/><m:naryLim m:val='undOvr'/></m:mathPr></w:WordDocument></xml><![endif]-->";
//    //其中content为ueditor生成的内容
//    String h = " <html xmlns:v='urn:schemas-microsoft-com:vml'xmlns:o='urn:schemas-microsoft-com:office:office'xmlns:w='urn:schemas-microsoft-com:office:word'xmlns:m='http://schemas.microsoft.com/office/2004/12/omml'xmlns='http://www.w3.org/TR/REC-html40'  ";
//    content =h+"<head>"+"<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+str+"</head><body>"+content+"</body> </html>";
//    byte b[] = content.getBytes("utf-8");  //这里是必须要设置编码的，不然导出中文就会乱码。
//    ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中
//
//    /*
//     * 关键地方
//     * 生成word格式 */
//    POIFSFileSystem poifs = new POIFSFileSystem();
//    DirectoryEntry directory = poifs.getRoot();
//    DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
//    String fileName=member.getMemberName();
//    FileOutputStream os = new FileOutputStream(path+File.separator+fileName+".doc");
//    //输出文件
//		            poifs.writeFilesystem(os);
//		            bais.close();
//		            os.close();






}




