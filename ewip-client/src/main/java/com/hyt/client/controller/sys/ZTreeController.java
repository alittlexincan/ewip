package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IZTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: ZTree树管理控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@RestController
@RequestMapping("/tree")
public class ZTreeController {

    @Autowired
    private IZTreeService zTreeService;

    /**
     * 获取地区树
     * @param map
     * @return
     */
    @PostMapping("/area")
    JSONObject getAreaTree(@RequestParam Map<String,Object> map){
        return this.zTreeService.getAreaTree(map);
    }

}
