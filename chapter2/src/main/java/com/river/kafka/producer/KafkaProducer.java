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
    @PostMapping("/chapter2/message/{message}")
    public String sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1", normalMessage);
        return "success";
    }

    @PostMapping("/chapter2/callbackOne/{message}")
    public String sendMessage2(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            log.info("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, failure -> {
            log.info("发送消息失败:" + failure.getMessage());
        });
        return "success";
    }

    @PostMapping("/chapter2/callbackTwo/{message}")
    public String sendMessage3(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage)
                .addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.info("发送消息失败：" + ex.getMessage());
                    }

                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
                        log.info("发送消息成功：" + result.getRecordMetadata().topic() + "-"
                                + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
                    }
                });
        return "success";
    }

    @PostMapping("/chapter2/transaction")
    public String sendMessage7() {
        // 声明事务：后面报错消息不会发出去
        String result = kafkaTemplate.executeInTransaction(operations -> {
            operations.send("topic1", "test executeInTransaction 1");
            operations.send("topic1", "test executeInTransaction 2");
            operations.send("topic2", "test executeInTransaction 31");
            operations.send("topic2", "test executeInTransaction 32");
            operations.send("topic2", "test executeInTransaction 33");
            operations.send("topic2", "test executeInTransaction 34");
            operations.send("topic3", "test executeInTransaction 4");
            return "success";
        });
        return result;
    }

}
