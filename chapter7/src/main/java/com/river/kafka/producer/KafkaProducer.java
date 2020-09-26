package com.river.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

/**
 * @program: KafkaDemo
 * @description:
 * @author: River
 * @create: 2020-09-25 22:05
 **/
@RestController
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 发送消息
    @PostMapping("/chapter7/message")
    public String sendMessage1() {
        Random rnd = new Random();
        int events = 10;
        for (long nEvents = 0; nEvents < events; nEvents++) {
            long runtime = new Date().getTime();
            String number = String.valueOf(rnd.nextInt(255));
            try {
                kafkaTemplate.send(new ProducerRecord<>("topic7", number, String.valueOf(runtime)));
                System.out.println("Sent message: (" + number + ", " + runtime + ")");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
