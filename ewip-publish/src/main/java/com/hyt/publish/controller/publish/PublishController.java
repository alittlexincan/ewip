package com.hyt.publish.controller.publish;

import com.alibaba.fastjson.JSONObject;
import com.hyt.publish.service.record.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 信息发布接口控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private IRecordService recordService;


    @PostMapping("/")
    public void publish(@RequestBody Map<String, Object> map){
        System.out.println("发布接口");

        JSONObject json = new JSONObject(map);
        System.out.println(json);
        // 如果record国突标识为1，则需要对接国突
        // 生成CAP协议文件，将其通过FTP上传到国突平台对应的文件夹下
        int record = json.getInteger("record");
        if(record == 1){
            this.recordService.record(json);
        }


    }
}
