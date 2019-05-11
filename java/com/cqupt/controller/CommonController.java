package com.cqupt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mall")
public class CommonController {
    /**
     * 网站首页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    /**
     * 产品详细信息
     * @return
     */
    @RequestMapping("product")
    public ModelAndView product(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("product");
        return mv;
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
}
