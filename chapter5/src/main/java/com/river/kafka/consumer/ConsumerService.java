package com.river.kafka.consumer;

/**
 * @program: KafkaDemo
 * @description: MessageListener
 * @author: River
 * @create: 2020-09-26 13:41
 **/

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

/**
 * Created by SYJ on 2017/3/21.
 */
@Service
@Slf4j
public class ConsumerService implements MessageListener<String, Object> {
    /**
     * 消息监听方法
     * @param record
     */
    @Override
    public void onMessage(ConsumerRecord<String, Object> record) {
        log.info("Before receiving:" + record.toString());
    }
}
