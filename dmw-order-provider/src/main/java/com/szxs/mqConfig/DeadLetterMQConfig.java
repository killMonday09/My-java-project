package com.szxs.mqConfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadLetterMQConfig {

    /**
     * 订单交换机
     */
    private String orderExchange = "mayikt.order.exchange";

    /**
     * 订单队列
     */
    private String orderQueue = "mayikt.order.queue";

    /**
     * 订单路由key
     */
    private String orderRoutingKey = "mayikt.order.routingKey";

    /**
     * 死信交换机
     */
    private String dlxExchange = "mayikt.dlx.exchange";

    /**
     * 死信队列
     */
    private String dlxQueue = "mayikt.dlx.queue";

    /**
     * 死信路由
     */
    private String dlxRoutingKey = "mayikt.dlx.routingKey";

    /**
     * 声明死信交换机
     */
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(dlxExchange);
    }

    /**
     * 声明死信队列
     */
    @Bean
    public Queue dlxQueue() {
        return new Queue(dlxQueue);
    }

    /**
     * 声明订单业务交换机
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(orderExchange);
    }

    /**
     * 声明订单队列 核心操作一
     */
    @Bean
    public Queue orderQueue() {
        Map<String, Object> arguments = new HashMap<String, Object>(2);
        // 绑定我们的死信交换机
        arguments.put("x-dead-letter-exchange", dlxExchange);
        // 绑定我们的路由key
        arguments.put("x-dead-letter-routing-key", dlxRoutingKey);
        return new Queue(orderQueue, true, false, false, arguments);
    }

    /**
     * 绑定订单队列到订单交换机
     */
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(orderRoutingKey);
    }

    /**
     * 绑定死信队列到死信交换机
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with(dlxRoutingKey);
    }
}
