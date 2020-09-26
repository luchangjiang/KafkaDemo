package com.river.kafka.consumer;

/**
 * @program: KafkaDemo
 * @description: MessageListener
 * @author: River
 * @create: 2020-09-26 13:41
 **/

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by SYJ on 2017/3/21.
 */
@Component
@Slf4j
public class ConsumerService1 {
    /**
     * topics: 配置消费topic，以数组的形式可以配置多个
     * groupId: 配置消费组为”xiaofeng1“
     *
     * @param message
     */
    @KafkaListener(topics = {"${kafka.test.topic}"},groupId = "xiaofeng1")
    public void consumer(String message) {
        log.info("groupId = xiaofeng1, message = " + message);
    }

}
