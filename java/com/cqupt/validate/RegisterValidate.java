package com.cqupt.validate;

import com.cqupt.mapper.UserMapper;
import com.cqupt.pojo.User;
import com.cqupt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.PostConstruct;

@Component
public class RegisterValidate implements Validator {

    @Autowired
    private UserService userService = null;

    public static RegisterValidate registerValidate;
    @PostConstruct
    public void init(){
        registerValidate=this;
        registerValidate.userService=this.userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }


    /**
     * 注册输入校验
     * 用户名：不能为空
     * 密码：不小于6位且不能由纯数字组成
     * 确认密码：与密码相同
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;
        String userName = user.getUserName();
        if(userName==null||userName==""){
            //使用reject()无法将错误绑定到userName中
            errors.rejectValue("userName",null,"用户名不能为空");
        }else {
            User user2 = registerValidate.userService.getUserByName(userName);
            if(user2!=null) {
                errors.rejectValue("userName", null, "用户名已存在");
            }else{
                String password = user.getPassword();
                if(password==null||password=="") {
                    errors.rejectValue("password", null, "密码不能为空");
                }else if(password.length()<6){
                    errors.rejectValue("password",null,"密码不能小于六位");
                }else{
                    int flag=0;
                    for(int i=0;i<password.length();i++){
                        if(!Character.isDigit(password.charAt(i))){
                            flag=1;
                        }
                    }
                    if(flag==0){
                        errors.rejectValue("password",null,"密码不能由存数字构成");
                    }else  if(user.getPassword2()==null||user.getPassword2()==""){
                        errors.rejectValue("password2",null,"请确认密码");
                    } else if(!password.equals(user.getPassword2())){
                        errors.rejectValue("password2",null,"两次输入的密码不同");
                    }
                }

            }

        }

    }
}
