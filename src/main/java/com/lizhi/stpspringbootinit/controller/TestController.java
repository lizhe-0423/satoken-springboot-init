package com.lizhi.stpspringbootinit.controller;

import cn.dev33.satoken.util.SaResult;
import com.lizhi.stpspringbootinit.util.TooQrCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Resource
    RedisTemplate<String,Object> stringRedisTemplate;


    @RequestMapping("get")
    public void get(){
        TooQrCode.generateTooQrCode("http://hutool.cn/");
    }

    @RequestMapping("redisSet")
    public void redisSet(){
        System.out.println("进入 redis 测试页面");
        stringRedisTemplate.opsForValue().set("hello","hello,world");

    }


}
