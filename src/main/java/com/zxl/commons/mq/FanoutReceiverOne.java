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
@RabbitListener(queues = "fanout.A")
public class FanoutReceiverOne {

    @RabbitHandler
    public void process(String message) {
        System.out.println("广播模式1: " + message);
    }
}
