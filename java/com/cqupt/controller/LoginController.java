package com.cqupt.controller;

import com.cqupt.pojo.User;
import com.cqupt.service.UserService;
import com.cqupt.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validator;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 验证码制作Service
     */
    @Autowired
    private ValidateCodeService validateCodeService = null;

    @Autowired
    private UserService userService = null;


    /**
     * 验证码制作
     * @param request
     * @param response
     */
    @RequestMapping("/validate")
    public void checkCode(HttpServletRequest request, HttpServletResponse response){
        try {
            BufferedImage bi = validateCodeService.generateImage(request);
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(bi,"jpg",out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     * @param user
     * @param request
     * @param response
     */
    @RequestMapping("check")
    public void Check(User user, HttpServletRequest request,HttpServletResponse response){
        String userName = user.getUserName();
        String checkCode = user.getCheckCode();
        String password = user.getPassword();
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(userName==null||userName==""){
            out.print("请输入用户名");
        }else {
            if(password==null||password==""){
                out.print("请输入密码");
            }else if(checkCode==null||checkCode==""){
                out.print("请输入验证码");
            }else{
                boolean isRight = validateCodeService.checkCode(checkCode,request);
                if(isRight){
                    User user1 = userService.getUserByName(userName);
                    if(user1==null){
                        out.print("用户名或密码错误");
                    }else if(!user1.getPassword().equals(password)){
                        out.print("用户名或密码错误");
                    }else {
                        request.getSession().setAttribute("user",user.getUserName());
                        out.print("success");
                    }
                }else{
                    out.print("验证码错误");
                }
            }
        }
    }

    @RequestMapping("logout")
    public void logout(HttpServletRequest request,HttpServletResponse response){
        String userName = (String) request.getSession().getAttribute("user");
        if(userName!=""&&userName!=null){
            request.getSession().removeAttribute("user");
        }
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print("success");
    }

}
