package com.cqupt.controller;

import com.cqupt.service.UserService;
import com.cqupt.validate.RegisterValidate;
import com.cqupt.pojo.User;
import com.cqupt.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService = null;

    /**
     * 校验器绑定
     * @param binder
     */
    @InitBinder
    public void initBinder(DataBinder binder){
        binder.setValidator(new RegisterValidate());
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public void register(@Valid @ModelAttribute("error") User user, Errors errors,HttpServletResponse response){
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (errors.hasErrors()){
            List<FieldError> errorList = errors.getFieldErrors();
            out.print("error");
            for(FieldError error:errorList){
                out.print(error.getDefaultMessage());
            }
        }else{
            int result = userService.insertUser(user);
            if(result<1){//注册失败
                out.print("fail");
            }else{
                out.print("success");
            }

        }
    }

    /**
     * 验证用户名是否已存在
     * @param userName
     * @param response
     */
    @RequestMapping("/checkUserName")
    public void checkUserName(String userName,HttpServletResponse response){
        try {
            PrintWriter out = response.getWriter();
            if(userService.getUserByName(userName)!=null){
                out.print("用户名已存在");
            }else{
                out.print("success");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
