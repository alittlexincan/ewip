package com.hyt.server.controller.ueditor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.ueditor.Ueditor;
import com.hyt.server.service.publish.IPublishService;
import com.hyt.server.service.ueditor.IUeditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("ueditor")
public class UeditorController {
    @Autowired
    private IUeditorService ueditorService;

    /**
     * 注入分发平台接口
     */
    @Autowired
    private IPublishService publishService;

    /**
     * 保存产品
     * @param map
     * @return
     */
    @PostMapping("/insert")
    public ResultObject<Object> insert(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);

        Ueditor ueditor = JSON.parseObject(json.toJSONString(), new TypeReference<Ueditor>() {});
        int num = this.ueditorService.insert(map);
        if(num>0){
            return ResultResponse.make(200,"添加成功",ueditor);
        }
        return ResultResponse.make(500,"添加失败",null);
    }
    /**
     * 发送邮件
     * @param map
     * @return
     */
    @GetMapping("/sendMail")
    public void sendMail(@RequestParam Map<String,Object> map){

        // 获取用户信息，并组装数据
        JSONObject json = this.ueditorService.sendData(map);

        if(json.getInteger("code") == 200){

            Map<String, Object> param = new HashMap<>(json);

            this.publishService.sendEmail(json);
        }

    }
}
