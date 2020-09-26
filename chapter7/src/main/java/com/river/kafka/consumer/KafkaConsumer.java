package com.river.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @program: KafkaDemo
 * @description:
 * @author: River
 * @create: 2020-09-25 22:09
 **/
@Component
@Slf4j
public class KafkaConsumer {
    // 监听器
    @KafkaListener(topics = {"topic7"})
    public void onMessage1(ConsumerRecord<?, ?> record){
        System.out.println("消费成功："+record.topic()+"-"+record.partition()+"-"+record.value());
    }
}
