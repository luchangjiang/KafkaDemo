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
    // 将这个异常处理器的BeanName放到@KafkaListener注解的errorHandler属性里面
    @KafkaListener(topics = {"topic1"},errorHandler = "consumerAwareErrorHandler")
    @SendTo("topic3")
    public String onMessage4(ConsumerRecord<?, ?> record) throws Exception {
        log.info("简单消费-" + record.value());
        return "forward";
    }

    // 批量消费也一样，异常处理器的message.getPayload()也可以拿到各条消息的信息
    @KafkaListener(topics = "topic2",errorHandler="consumerAwareErrorHandler")
    public void onMessage5(List<ConsumerRecord<?, ?>> records) throws Exception {
        log.info("批量消费一次...");
        throw new Exception("批量消费-模拟异常");
    }

    // 消息过滤监听
    @KafkaListener(topics = {"topic3"},containerFactory = "filterContainerFactory")
    public void onMessage6(ConsumerRecord<?, ?> record) {
        log.info("topic3消费过滤消息：" + record.value().toString());
    }
}
