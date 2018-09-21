package com.hyt.client.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright (C), 2015-2018
 * @FileName: PageController
 * @Author:   JiangXincan
 * @Date:     2018-5-8 15:57
 * @Description: ${DESCRIPTION}
 * @History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名         修改时间          版本号             描述
 */
@Controller
public class PageController {

    /**
     * 进入登录界面
     * @return
     */
    @RequestMapping("/")
    public String signIn(){
        return "main/login";
    }

    /**
     * 进入登录界面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "main/index";
    }

    /**
     * 进入登录界面
     * @return
     */
    @RequestMapping("/home")
    public String home(){
        return "main/home";
    }

    /**
     * 页面跳转通用方法
     * @param model 模块编码
     * @param name  模块名称
     * @return
     */
    @RequestMapping("/page/{model}/{name}")
    public ModelAndView page(@PathVariable("model") String model, @PathVariable("name") String name, @RequestParam Map<String, Object> map){
        return new ModelAndView(model + "/" + name, map);
    }

    /**
     *  页面跳转通用方法
     * @param model  模块目录
     * @param name   模块名称
     * @param option 传输数据
     * @return
     */
    @RequestMapping("/page/{model}/{name}/{option}")
    public ModelAndView page(@PathVariable("model") String model,@PathVariable("name") String name, @PathVariable("option") String option){
        Map<String, Object> map = new HashMap<>();
        map.put("id", option);
        return new ModelAndView(model + "/" + name, map);
    }

}
