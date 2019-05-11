package com.cqupt.service;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

public interface ValidateCodeService {
    /**
     * 生成验证码图片
     * @param request
     * @return
     */
    public BufferedImage generateImage(HttpServletRequest request);

    /**
     * 核对验证码
     * @param code
     * @return
     */
    public boolean checkCode(String code,HttpServletRequest request);
}
