package com.river.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: KafkaDemo
 * @description:
 * @author: River
 * @create: 2020-09-25 22:05
 **/
@Component
@Slf4j
@ConfigurationProperties
public class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${kafka.test.topic}")
    String testTopic;

    public void sendTest(String msg){
        kafkaTemplate.send(testTopic, msg);
    }

}
