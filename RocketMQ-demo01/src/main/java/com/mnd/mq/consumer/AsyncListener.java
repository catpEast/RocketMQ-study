package com.mnd.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = "topic-a",
        consumerGroup = "consumer-group1")
public class AsyncListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        log.info("topic-a 收到消息 msg={}", s);
    }
}
