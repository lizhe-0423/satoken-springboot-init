package com.lizhi.stpspringbootinit.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static com.lizhi.stpspringbootinit.constant.AmqpConstant.TOP_QUEUE;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 */
@Slf4j
@Component
public class RabbitMQListener {


    @RabbitListener(queues = TOP_QUEUE)
    public void receiveOrder(Message message){
        log.info("收到订单{}", new String(message.getBody()));
    }
}
