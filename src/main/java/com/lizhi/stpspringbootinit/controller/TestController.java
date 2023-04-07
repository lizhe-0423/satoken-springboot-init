package com.lizhi.stpspringbootinit.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.RandomUtil;
import com.lizhi.stpspringbootinit.model.entity.User;
import com.lizhi.stpspringbootinit.util.RedisUtil;
import com.lizhi.stpspringbootinit.util.TooQrCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.lizhi.stpspringbootinit.constant.UserConstant.USER_LOGIN_STATE;

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
    RedisTemplate<String,Object> redisTemplate;

    @Resource
    RedisUtil redisUtil;


    @RequestMapping("getTooQrCode")
    public void get(){
        TooQrCode.generateTooQrCode("http://hutool.cn/");
    }

    @RequestMapping("setRedStr")
    public void redisSet(){
        redisUtil.setString("测试","测试");
    }
    @RequestMapping("getRedStr")
    public String redisGet(){
        String string = redisUtil.getString("测试");
        return string;
    }
    @RequestMapping("setRedStrEx")
    public void redisSetEx(){
        redisUtil.setStringEx("测试2","测试2",10, TimeUnit.SECONDS);
    }


    @RequestMapping("delRedStr")
    public void delRedStr(){
        redisUtil.del("测试");
    }

    @RequestMapping("incr")
    @ApiOperation("自增")
    public long redisIncr(){
        long incr = redisUtil.incr("1641766009775976450", 1);
        return incr;
    }
    @RequestMapping("decr")
    @ApiOperation("自减")

    public long redisDecr(){
        long decr = redisUtil.decr("1641766009775976450", 1);
        return decr;
    }

    @RequestMapping("leftPop")
    @ApiOperation("队列左出")
    public void leftPop(){
        redisUtil.queueLeftPop("队列");
    }
    @RequestMapping("rightPop")
    @ApiOperation("队列右出")
    public void rightPop(){
        redisUtil.queueRightPop("队列");
    }
    @RequestMapping("push")
    @ApiOperation("进队列")
    public void push(){
        String string = String.valueOf(RandomUtil.randomInt(1, 10000));
        redisUtil.queuePush("队列",string);
    }

    /**
     * 添加字典
     */
    @RequestMapping("hashPush")
    @ApiOperation("添加字典")
    public void hashPush(){
        redisUtil.hashPush("字典","用户","name:lizhi");
        redisUtil.hashPush("字典","用户","phone:17749038218");
        redisUtil.hashPush("字典","用户","address:山东省德州市陵城区荔枝小区");
    }

    @RequestMapping("setAdd")
    @ApiOperation("添加集合元素")
    public void setAdd(){
        User loginUser = (User) StpUtil.getSession().get(USER_LOGIN_STATE);
        long loginUserId = loginUser.getId();
        redisUtil.setAddLong("id", String.valueOf(loginUserId));
    }

    @RequestMapping("zSetAdd")
    @ApiOperation("添加z集合元素")
    public void zSetAdd(){
        User loginUser = (User) StpUtil.getSession().get(USER_LOGIN_STATE);
        long loginUserId = loginUser.getId();
        redisUtil.zSet("粉丝列表","10001",10);
        redisUtil.zSet("粉丝列表","10002",20);
        redisUtil.zSet("粉丝列表","10003",30);
        redisUtil.zSet("粉丝列表","10004",40);

    }



}
