package com.hyt.publish.controller.publish;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.publish.service.record.IRecordService;
import com.hyt.publish.service.wechat.IWechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 信息发布接口控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/publish")
@EnableAsync
public class PublishController {

    /**
     * 发布渠道：对接微信接口
     */
    @Autowired
    private IWechatService wechatService;

    /**
     * 国突平台对接接口
     */
    @Autowired
    private IRecordService recordService;


    /**
     * 渠道发布
     * @param map
     */
    @PostMapping("/")
    public void publish(@RequestBody Map<String, Object> map){
        JSONObject json = new JSONObject(map);
        log.info("接收推送数据：" + json.toJSONString());
        JSONArray channelArray = json.getJSONArray("channel");
        for(int i = 0; i<channelArray.size(); i++){

            JSONObject channel = channelArray.getJSONObject(i);

            if(channel.getString("channelCode").equals("SMS")) this.wechatService.wechat(json);


        }
        // 如果record国突标识为1，则需要对接国突
        // 生成CAP协议文件，将其通过FTP上传到国突平台对应的文件夹下
        int record = json.getInteger("record");
        if(record == 1){
            this.recordService.record(json);
        }

    }
}
