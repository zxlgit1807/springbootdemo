package com.zxl.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: ZXL
 * @Date: 2018/9/20
 * @Description: 队列配置
 */
@Configuration
public class RabbitConfig {

    final static String topic_one = "topic.one";
    final static String topic_two = "topic.two";

    @Bean
    public Queue queue() {
        return new Queue("hello");
    }

    // topic
    @Bean
    public Queue queueMessage() {
        return new Queue(RabbitConfig.topic_one);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(RabbitConfig.topic_two);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
    // 队列绑定到交换机
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.one");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
    // topic end

    // 广播模式/订阅模式
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

}
