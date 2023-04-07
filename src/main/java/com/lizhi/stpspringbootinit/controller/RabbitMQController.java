package com.lizhi.stpspringbootinit.controller;

import com.lizhi.stpspringbootinit.model.entity.Order;
import com.lizhi.stpspringbootinit.service.impl.RabbitMQService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.core.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 */
@RestController
@RequestMapping("rabbitMQ")
@Slf4j
public class RabbitMQController {

    @Resource
    RabbitMQService rabbitMQService;


    @RequestMapping("send")
    @ApiOperation("发送订单")
    public void sendOrder(){

        Order order = new Order();
        order.setId(1000111020);
        order.setName("wang");
        order.setPrice(16.7);


        rabbitMQService.sendOrder(order);
    }



}
