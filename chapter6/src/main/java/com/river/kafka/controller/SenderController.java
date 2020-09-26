package com.river.kafka.controller;

import com.river.kafka.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: KafkaDemo
 * @description: 发送消息
 * @author: River
 * @create: 2020-09-26 20:18
 **/
@RestController
@Slf4j
public class SenderController {
    @Autowired
    KafkaProducer sender;

    @PostMapping("/chapter6/send")
    public void send() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            log.info("send message = " + i);
            sender.sendTest(i + "");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
