package com.wxc.kafka.java.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Properties;

/**
 * @author wangxiaocheng
 */
public class ProducerDemo {
    /**
     *
     * 1、启动zookeeper：zookeeper-server-start.bat ..\..\config\zookeeper.properties
     * 2、启动kafka：kafka-server-start.bat ..\..\config\server.properties
     * 3、创建主题：kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka-test-topic
     * 4、查看主题：kafka-topics.bat --list --zookeeper localhost:2181
     * 5、启动生产者：kafka-console-producer.bat --broker-list localhost:9092 --topic kafka-test-topic
     * 6、启动消费者：kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic kafka-test-topic --from-beginning
     *
     */
    @Test
    public void producerTest(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("metadata.broker.list","localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        // 发送业务消息
        // 读取文件 读取内存数据库 读socket端口
        for (int i = 1; i <= 100; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            producer.send(new ProducerRecord<String, String>("wordcount",
                    i+" said "+ i + " love you baby for " + i + " times,will you have a nice day with me tomorrow"));
        }
    }
}
