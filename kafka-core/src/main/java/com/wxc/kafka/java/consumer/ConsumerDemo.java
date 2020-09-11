package com.wxc.kafka.java.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.errors.WakeupException;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wangxiaocheng
 */
public class ConsumerDemo {
    @Test
    public void consumerTest(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        while(true){
            Map<String, List<PartitionInfo>> maps = kafkaConsumer.listTopics();
            System.out.println("监听topics="+maps.keySet());
            Set<String> sets = new HashSet<>();
            for (String topic : maps.keySet()) {
                // 制定规则，监听哪一些的topic
                if(topic.startsWith("word")){
                    sets.add(topic);
                }
            }
            kafkaConsumer.subscribe(sets);
            long startTime = System.currentTimeMillis();
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, value = %s, topic = %s", record.offset(), record.value(), record.topic());
                    System.out.println("=====================>");
                }
                long endTime = System.currentTimeMillis();
                if(endTime - startTime > 30000){
                    System.out.println("------------------------------------------------------------------");
                    break;
                }
            }
    }

    }
}
