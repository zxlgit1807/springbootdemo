package com.zxl.commons.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: ZXL
 * @Date: 2018/9/20
 * @Description:
 */
@Component
@RabbitListener(queues = "topic.two")
public class TopicReceiverTwo {

    @RabbitHandler
    public void process(String message) {
        System.out.println("主题订阅2: " + message);
    }
}
