package com.cqupt.service.impl;

import com.cqupt.service.ValidateCodeService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private Producer producer = null;


    @Override
    public BufferedImage generateImage(HttpServletRequest request) {
        String code = producer.createText();
        request.getSession().setAttribute("code",code);
        BufferedImage bi = producer.createImage(code);
        return bi;
    }

    @Override
    public boolean checkCode(String code,HttpServletRequest request) {
        String imageCode = (String) request.getSession().getAttribute("code");
        return imageCode.equals(code);
    }
}
