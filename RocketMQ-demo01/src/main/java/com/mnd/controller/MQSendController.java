package com.mnd.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/mq")
public class MQSendController {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    
    /**
     * 跟踪源码发现通过 RocketMQTemplate 调用最终还是调用的syncSend
     * @param topic
     * @param msg
     * @throws Exception
     */
    @GetMapping("/send")
    public void convertAndSend(String topic, String msg) throws Exception{
        rocketMQTemplate.convertAndSend(topic, msg);
    }
    
    @GetMapping("/syncSend")
    public SendResult syncSend(String topic, String msg){
        return rocketMQTemplate.syncSend(topic, msg);
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
    }

    @GetMapping("/sendOneWay")
    public void sendOneWay(String topic, String msg){
        rocketMQTemplate.sendOneWay(topic, msg);
    }


    /** --------------------------- 顺序消息 ---------------------------------- */
    public static String hashKey = "123456789";
    @GetMapping("/syncSendOrderly")
    public SendResult syncSendOrderly(String topic, String msg){
        return rocketMQTemplate.syncSendOrderly(topic, msg, hashKey);
    }

    @GetMapping("/asyncSendOrderly")
    public void asyncSendOrderly(String topic, String msg){

        rocketMQTemplate.asyncSendOrderly(topic, msg, hashKey, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("异步发送顺序消息成功 topic : {}  msg : {}", topic, msg);
            }

            @Override
            public void onException(Throwable throwable) {
            }
        });

    }

    @GetMapping("/sendOneWayOrderly")
    public void sendOneWayOrderly(String topic, String msg){
        rocketMQTemplate.sendOneWayOrderly(topic, msg, hashKey);
        log.info("单向方式发送顺序消息------");
    }
    
}
