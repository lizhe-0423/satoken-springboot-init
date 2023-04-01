package com.lizhi.stpspringbootinit.controller;

import com.lizhi.stpspringbootinit.util.TooQrCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    com.lizhi.stpspringbootinit.util.TooQrCode TooQrCode;
    @RequestMapping("get")
    public void get(){
        TooQrCode.generateTooQrCode("http://hutool.cn/");
    }
}
