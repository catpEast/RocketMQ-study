package com.mnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.mnd")
public class MQApplication {

    public static void main(String[] args) {

        SpringApplication.run(MQApplication.class, args);
    }
}
