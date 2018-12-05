package com.hyt.client.service.ueditor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Service("ueditorService")
@FeignClient("EWIP-SERVER")
public interface IUeditorService {


    /**
     * 添加信息
     * @param map
     * @return
     */
    @PostMapping("/ueditor/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);

    /**
     * 发送邮件
     * @param newMap
     * @return
     */
    @GetMapping("/ueditor/sendMail")
    void sendMail(@RequestParam Map<String,Object> newMap);
}
