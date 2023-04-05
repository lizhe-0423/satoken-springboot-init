package com.lizhi.stpspringbootinit.util;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.poi.ss.formula.functions.T;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 * redis 操作
 * string:缓存结构体信息(用户信息、登录信息、token)、计数功能(统计点赞、粉丝、关注数量)
 * list:异步队列(秒杀场景、库存销量)
 * hash:缓存结构体(属性字段、单字段也可以进行计数)
 * set:中奖id(去重)
 * zset:可以用来存粉丝列表、关注列表等
 *
 */
@Component
public class RedisUtil {

    @Resource
    RedisTemplate<String,Object> stringRedisTemplate;


    /**
     * redis 存储 string 类型
     * @param str1 key
     * @param str2 value
     */
    public void setString(@NotNull  String str1, String str2){
        stringRedisTemplate.opsForValue().set(str1,str2);
    }

    /**
     * redis 根据key查询 value
     * @param str key
     * @return
     */
    public String getString(@NotNull String str){
        String o = (String) stringRedisTemplate.opsForValue().get(str);
        return  o;
    }

    /**
     * redis 设置string存储时间
     * @param str1 key
     * @param str2 value
     * @param time int
     * @param timeUnit 期效
     */
    public void  setStringEx(@NotNull String str1, String str2, int time, TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(str1,str2,time,timeUnit);
    }

    /**
     * 根据key 删除操作
     * @param string key
     */
    public void del(String string){
        stringRedisTemplate.delete(string);
    }

    /**
     * 根据nums 自增
     * @param str key
     * @param nums 自增
     * @return
     */
    public Long incr(String str,int nums){
        long increment = stringRedisTemplate.opsForValue().increment(str, nums);
        return increment;
    }

    /**
     * 根据nums 自减
     * @param str
     * @param nums
     * @return
     */
    public Long decr(String str,int nums){
        Long decrement = stringRedisTemplate.opsForValue().decrement(str, nums);
        return decrement;
    }

    /**
     * 队列左出
     * @param str1 key
     */
    public void queueLeftPop(String str1){
        stringRedisTemplate.opsForList().leftPop(str1);
    }

    /**
     * 队列右出
     * @param str1 key
     */
    public void queueRightPop(String str1){
        stringRedisTemplate.opsForList().rightPop(str1);
    }

    /**
     * 队列左进
     * @param str1 key
     * @param obj value
     */
    public void queuePush(String str1,String obj){
        stringRedisTemplate.opsForList().leftPush(str1,obj);
    }



}
