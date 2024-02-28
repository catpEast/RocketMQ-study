package com.mnd.controller;

import com.mnd.MQApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class MQSendControllerTest {


    @Autowired
    private MQSendController mqSendController;

    @Autowired
//    private MockMvc mockMvc;
    private static final String topic = "topic-a";

    @Test
    void syncSend() throws Exception {
        for (int i = 0; i < 1000; i++) {
            mqSendController.syncSend(topic, "hello " + i);
        }
    }

    @Test
    void asyncSend() {
        for (int i = 0; i < 100; i++) {
            mqSendController.asyncSend(topic, "sync msg " + i);
        }
    }

    @Test
    void sendOneWay() {
        for (int i = 0; i < 100; i++) {
            mqSendController.sendOneWay(topic, "sync msg " + i);
        }
    }

    @Test
    void syncSendOrderly() {
        for (int i = 0; i < 1000; i++) {
            mqSendController.syncSendOrderly(topic, "sync msg " + i);
        }
    }

    @Test
    void asyncSendOrderly() {
        for (int i = 0; i < 1000; i++) {
            mqSendController.asyncSendOrderly(topic, "sync msg " + i);
        }
    }

    @Test
    void sendOneWayOrderly() {
        for (int i = 0; i < 1000; i++) {
            mqSendController.sendOneWayOrderly(topic, "sync msg " + i);
        }
    }
}