package com.hyt.client.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        return "main/signIn";
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
    public ModelAndView page(@PathVariable("model") String model,@PathVariable("name") String name){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(model + "/" + name);
        return mv;
    }

    /**
     *  页面跳转通用方法
     * @param model  模块目录
     * @param name   模块名称
     * @param option 模块操作
     * @return
     */
    @RequestMapping("/page/{model}/{name}/{option}")
    public ModelAndView page(@PathVariable("model") String model,@PathVariable("name") String name, @PathVariable("option") String option){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(model + "/" + name);
        return mv;
    }

}
