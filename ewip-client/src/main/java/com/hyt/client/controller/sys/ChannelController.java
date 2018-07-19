package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IChannelService;
import com.hyt.client.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 渠道手段信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("channel")
public class ChannelController {

    /**
     * 获取上传的文件夹，具体路径参考application.properties中的配置
     */
    @Value("${web.upload-path}")
    private String uploadPath;

    /**
     * 渠道图标上传文件夹
     */
    @Value("${web.channel-path}")
    private String channel;

    @Autowired
    private IChannelService channelService;

    /**
     * 添加渠道手段信息
     * @param map
     * @param file
     * @return
     */
    @PostMapping("/insert")
    JSONObject insert(@RequestParam Map<String,Object> map, @RequestParam("addFile") MultipartFile file ){
        JSONObject json = new JSONObject();
        // 文件开始上传
        String fileUrl = UploadFileUtil.upload(file, uploadPath, channel);
        // 上传成功走后台添加数据
        if(fileUrl!=null){
            // 添加渠道数据
            map.put("icon", fileUrl);
            json = this.channelService.insert(map);
        }
        return json;
    }

    /**
     * 修改渠道手段信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    JSONObject update(@RequestParam Map<String,Object> map, @RequestParam("updateFile") MultipartFile file ){

        JSONObject json = new JSONObject();
        if(file.isEmpty()){
            map.put("icon", this.channel + "/" + map.get("icon"));
            return this.channelService.update(map);
        }

        // 文件开始上传
        String fileUrl = UploadFileUtil.upload(file, uploadPath, channel);
        // 上传成功走后台添加数据
        if(fileUrl!=null){
            // 修改渠道数据
            map.put("icon", fileUrl);
            json = this.channelService.update(map);
        }
        return json;
    }

    /**
     * 根据地区id删除渠道手段信息
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id, @RequestParam Map<String, Object> map){
        JSONObject json = this.channelService.deleteById(id);
        if(json.getInteger("code") == 200){
            String fileName = this.uploadPath + map.get("fileName").toString();
            UploadFileUtil.deleteFile(fileName);
        }
        return json;
    }

    /**
     * 根据ids批量删除渠道手段信息
     * @param map
     * @return
     */
    @PostMapping("/delete")
    JSONObject deleteBatch(@RequestParam Map<String, Object> map){
        System.out.println(map);
        JSONObject json = this.channelService.deleteBatch(map.get("id").toString());
        if(json.getInteger("code") == 200){
            String[] fileName = map.get("fileName").toString().split(",");
            String[] url = new String[fileName.length];
            for(int i = 0; i<fileName.length; i++){
                url[i] = this.uploadPath + this.channel + "/" + fileName[i];
            }
            UploadFileUtil.deleteFile(url);
        }
        return json;
    }

    /**
     * 根据地区id查询渠道手段详细信息
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id){
        return this.channelService.selectById(id);
    }

    /**
     * 分页查询渠道手段信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map){
        return this.channelService.selectAll(map);
    }

    /**
     * 查询渠道手段信息
     * @param map
     * @return
     */
    @PostMapping("/list")
    JSONObject selectList(@RequestParam Map<String,Object> map){
        return this.channelService.selectList(map);
    }

}
