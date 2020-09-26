package com.river.kafka.consumer;

/**
 * @program: KafkaDemo
 * @description: MessageListener
 * @author: River
 * @create: 2020-09-26 13:41
 **/

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by SYJ on 2017/3/21.
 */
@Component
@Slf4j
public class ConsumerService2 {
    /**
     * topics: 配置消费topic，以数组的形式可以配置多个
     * groupId: 配置消费组为”xiaofeng1“
     *
     * @param message
     */
    @KafkaListener(topics = {"${kafka.test.topic}"},groupId = "xiaofeng2")
    public void consumer(String message) {
        log.info("groupId = xiaofeng2, message = " + message);
    }

}
