package com.zxl.commons.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: ZXL
 * @Date: 2018/9/20
 * @Description: MQ接收类
 */
// 发送者和接收者的queue name必须一致
@Component
@RabbitListener(queues = "hello")
public class MsgReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("接收  : " + hello);
    }

}
