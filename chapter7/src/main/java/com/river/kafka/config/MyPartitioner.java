package com.river.kafka.config;

/**
 * @program: KafkaDemo
 * @description: 自定义分区规则
 * @author: River
 * @create: 2020-09-26 21:06
 **/

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class MyPartitioner implements Partitioner {

    @Override
    public void configure(Map<String, ?> configs) {
    }


    @Override
    public int partition(String topic, Object key, byte[] keyBytes,
                         Object value, byte[] valueBytes, Cluster cluster) {
        String k = (String) key;
        if(Integer.parseInt(k) % 2 == 0)
            return 0;
        else
            return 1;
    }

    @Override
    public void close() {
    }

}

