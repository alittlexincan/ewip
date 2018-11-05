package com.hyt.server.controller.ueditor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.sys.Area;
import com.hyt.server.entity.ueditor.Ueditor;
import com.hyt.server.service.ueditor.IUeditorService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("ueditor")
public class UeditorController {
    @Autowired
    private IUeditorService ueditorService;

    @PostMapping("/insert")
    public ResultObject<Object> insert(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);

        Ueditor ueditor = JSON.parseObject(json.toJSONString(), new TypeReference<Ueditor>() {});
        int num = this.ueditorService.insert(map);
        if(num>0){
            return ResultResponse.make(200,"添加地区成功",ueditor);
        }
        return ResultResponse.make(500,"添加地区失败",null);
    }

    @GetMapping("/sendMail")
    public void sendMail(@RequestParam Map<String,Object> map){
        ueditorService.sendMail(map);
    }
}
