package com.river.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/chapter3/message1/{message}")
    public String sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1", normalMessage);
        return "success";
    }

    // 发送消息
    @PostMapping("/chapter3/message2/{message}")
    public String sendMessage2(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1", normalMessage);
        kafkaTemplate.send("topic2", normalMessage + "1");
        kafkaTemplate.send("topic2", normalMessage + "2");
        kafkaTemplate.send("topic2", normalMessage + "3");
        kafkaTemplate.send("topic2", normalMessage + "4");
        kafkaTemplate.send("topic2", normalMessage + "5");
        return "success";
    }

    // 发送消息
    @PostMapping("/chapter3/filterMessage")
    public String sendMessage3() {
        kafkaTemplate.send("topic3", "aadw1");
        kafkaTemplate.send("topic3", "aaww1");
        kafkaTemplate.send("topic3", "dsadw1");
        kafkaTemplate.send("topic3", "fddw1");
        kafkaTemplate.send("topic3", "aadfeww1");
        return "success";
    }
}
