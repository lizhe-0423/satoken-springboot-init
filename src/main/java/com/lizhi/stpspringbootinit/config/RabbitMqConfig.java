package com.lizhi.stpspringbootinit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

import static com.lizhi.stpspringbootinit.constant.AmqpConstant.*;

/**
 * @author <a href="https://github.com/lizhe-0423">荔枝</a>
 * rabbitMQ 配置
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 配置消息转换器
     * @return
     */
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    // 定义 Direct 类型交换机
    @Bean
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(TOP_EXCHANGE).durable(true).build();
    }
    // 定义 queue 队列
    @Bean
    public Queue topQueue(){
        return QueueBuilder.durable(TOP_QUEUE).build();
    }

    @Bean
    public Binding bindingQueueExchange(@Qualifier("topQueue") Queue queue,@Qualifier("topicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("top.#").noargs();
    }

    public DirectExchange lonelyDirectExchange(){
        return new DirectExchange("lonelyDirectExchange");
    }
}
