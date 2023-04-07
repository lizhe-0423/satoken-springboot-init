package com.lizhi.stpspringbootinit.service.impl;

import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.lizhi.stpspringbootinit.model.entity.Order;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.lizhi.stpspringbootinit.constant.AmqpConstant.*;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 */
@Service
@Slf4j
public class RabbitMQService {


    @Resource
    RabbitTemplate rabbitTemplate;

    public void sendOrder(Order order){
        String newOrder = JSONUtil.toJsonStr(order);
        rabbitTemplate.convertAndSend(TOP_EXCHANGE,"top.#",newOrder);
        log.info("加入订单信息{}",order);
    }


}

