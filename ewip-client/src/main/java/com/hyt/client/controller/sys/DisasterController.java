package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IDisasterService;
import com.hyt.client.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 灾种管理控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("disaster")
public class DisasterController {

    /**
     * 获取上传的文件夹，具体路径参考application.properties中的配置
     */
    @Value("${web.upload-path}")
    private String uploadPath;

    /**
     * 灾种图标上传文件夹
     */
    @Value("${web.disaster-path}")
    private String disaster;

    @Autowired
    private IDisasterService disasterService;

    /**
     * 添加灾种信息
     * @param map
     * @return
     */
    @PostMapping("/insert")
    JSONObject insert(@RequestParam Map<String,Object> map) {
        return this.disasterService.insert(map);
    }

    /**
     * 修改灾种信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    JSONObject update(@RequestParam Map<String,Object> map){
        return this.disasterService.update(map);
    }

    /**
     * 修改灾种信息
     * @param map
     * @return
     */
    @PostMapping("/update/strategy")
    JSONObject updateStrategy(@RequestParam Map<String,Object> map){
        return this.disasterService.updateStrategyById(map);
    }

    /**
     * 添加灾种级别信息
     * @param map
     * @param file
     * @return
     */
    @PostMapping("/insert/level")
    JSONObject insert(@RequestParam Map<String,Object> map, @RequestParam("addFile") MultipartFile file ){

        if(file.isEmpty()){
            map.put("icon", "");
            return this.disasterService.insert(map);
        }

        JSONObject json = new JSONObject();
        // 文件开始上传
        String fileUrl = UploadFileUtil.upload(file, uploadPath, disaster);
        // 上传成功走后台添加数据
        if(fileUrl!=null){
            // 添加渠道数据
            map.put("icon", fileUrl);
            json = this.disasterService.insert(map);
        }
        return json;
    }

    /**
     * 修改灾种级别信息
     * @param map
     * @return
     */
    @PostMapping("/update/level")
    JSONObject update(@RequestParam Map<String,Object> map, @RequestParam("updateFile") MultipartFile file ){

        JSONObject json = new JSONObject();
        if(file.isEmpty()){
            map.put("icon", this.disaster + "/" + map.get("icon"));
            return this.disasterService.update(map);
        }

        // 文件开始上传
        String fileUrl = UploadFileUtil.upload(file, uploadPath, disaster);
        // 上传成功走后台添加数据
        if(fileUrl!=null){
            // 修改渠道数据
            map.put("icon", fileUrl);
            json = this.disasterService.update(map);
        }
        return json;
    }

    /**
     * 根据灾种id删除灾种级别信息
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id, @RequestParam Map<String, Object> map){
        JSONObject json = this.disasterService.deleteById(id);
        if(json.getInteger("code") == 200){
            String fileName = this.uploadPath + map.get("fileName").toString();
            UploadFileUtil.deleteFile(fileName);
        }
        return json;
    }

    /**
     * 根据ids批量删除灾种级别信息
     * @param map
     * @return
     */
    @PostMapping("/delete/level")
    JSONObject deleteLevelBatch(@RequestParam Map<String, Object> map){
        JSONObject json = this.disasterService.deleteBatch(map.get("id").toString());
        if(json.getInteger("code") == 200){
            String[] fileName = map.get("fileName").toString().split(",");
            String[] url = new String[fileName.length];
            for(int i = 0; i<fileName.length; i++){
                url[i] = this.uploadPath + this.disaster + "/" + fileName[i];
            }
            UploadFileUtil.deleteFile(url);
        }
        return json;
    }

    /**
     * 根据ids批量删除灾种信息
     * @param map
     * @return
     */
    @PostMapping("/delete")
    JSONObject deleteBatch(@RequestParam Map<String, Object> map){
        return  this.disasterService.deleteBatch(map.get("id").toString());
    }

    /**
     * 根据地区id查询灾种详细信息
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id){
        return this.disasterService.selectById(id);
    }

    /**
     * 分页查询灾种级别信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map){
        return this.disasterService.selectAll(map);
    }

}
