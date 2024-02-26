package com.mnd.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mq")
public class MQSendController {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send")
    public void convertAndSend(String topic, String msg) throws Exception{
        rocketMQTemplate.convertAndSend(topic, msg);
        
    }
    
    @GetMapping("/syncSend")
    public SendResult syncSend(String topic, String msg){
        SendResult sendResult = rocketMQTemplate.syncSend(topic, msg);
        System.out.println("同步方式发送消息------");
        return sendResult;
    }

    @GetMapping("/asyncSend")
    public void asyncSend(String topic, String msg){
        
        rocketMQTemplate.asyncSend(topic, msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("异步发送消息成功 topic : {}  msg : {}", topic, msg);
            }

            @Override
            public void onException(Throwable throwable) {
                
            }
        });
        
        System.out.println("异步方式发送消息------");
    }

    @GetMapping("/sendOneWay")
    public void sendOneWay(String topic, String msg){
        rocketMQTemplate.sendOneWay(topic, msg);
        System.out.println("单向方式发送消息------");
    }
}
