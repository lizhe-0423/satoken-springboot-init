package com.lizhi.stpspringbootinit.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import lombok.val;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 * 图片验证码
 */
public class Captcha {
    /**
     * 生成 扭曲干扰验证码
     * @param str 传入 密码进行验证
     * @return true 验证码正确 false 验证码错误
     */
    public boolean shearCaptcha(String str){
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
//        captcha.write("d:/shear.png");
        boolean verify = captcha.verify(str);
        return verify;
    }
}
