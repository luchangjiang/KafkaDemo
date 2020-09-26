package com.river.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
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
    /**
     * @KafkaListener注解所标注的方法并不会在IOC容器中被注册为Bean，
     * 而是会被注册在KafkaListenerEndpointRegistry中，
     * 而KafkaListenerEndpointRegistry在SpringIOC中已经被注册为Bean
     **/
    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 发送消息
    @PostMapping("/chapter4/message1/{message}")
    public String sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1", normalMessage);
        return "success";
    }

    // 定时启动监听器
    @PostMapping("/chapter4/start")
    public void startListener() {
        // "timingConsumer"是@KafkaListener注解后面设置的监听器ID,标识这个监听器
        if (!registry.getListenerContainer("timingConsumer").isRunning()) {
            registry.getListenerContainer("timingConsumer").start();
            System.out.println("启动监听器...");
        }
        if (registry.getListenerContainer("timingConsumer").isContainerPaused()) {
            registry.getListenerContainer("timingConsumer").resume();
            System.out.println("恢复监听器...");
        }
    }

    // 定时停止监听器
    @PostMapping("/chapter4/pause")
    public void shutDownListener() {
        registry.getListenerContainer("timingConsumer").pause();
        System.out.println("关闭监听器...");
    }
}
