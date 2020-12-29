package com.wxc.kafka.java.producer;

import com.alibaba.fastjson.JSON;
import com.wxc.kafka.spring.producer.OBDKafkaProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * @author wangxiaocheng
 */
public class ProducerDemo {
    /**
     *    cd /d d:software\kafka_2.12-2.6.0\bin\windows
     * 1、启动zookeeper：zookeeper-server-start.bat ..\..\config\zookeeper.properties
     * 2、启动kafka：kafka-server-start.bat ..\..\config\server.properties
     * 3、创建主题：kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka-test-topic
     * 4、查看主题：kafka-topics.bat --list --zookeeper localhost:2181
     * 5、启动生产者：kafka-console-producer.bat --broker-list localhost:9092 --topic kafka-test-topic
     * 6、启动消费者：kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic kafka-test-topic --from-beginning
     *
     */

    @Test
    public void producerTest() throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("metadata.broker.list","localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, RealtimeMessage> producer = new KafkaProducer<>(props);
        // 发送业务消息
        // 读取文件 读取内存数据库 读socket端口
        for (int i = 1; i <= 10000000; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RealtimeMessage message = new RealtimeMessage();
            String pre = i%2==0?"苏E":"沪C";
            message.setLicensePrefix(pre);
            message.setTradeCode("111");
            message.setUpdateTime(new Date());
            message.setId("123237L");
            message.setLicenseColor("hu");
            message.setStatus(1);
            message.setOxynitride(1.23);
            message.setPm(1.23);
            message.setVocs(1.23);
            message.setCo2(1.23);
            message.setCo(1.23);
            message.setToluene(1.23);
            message.setXylol(1.23);
            message.setCyclopentane(1.23);
            message.setBlackCarbon(1.23);
            message.setOrganicCarbon(1.23);
            message.setLocateTime(new Date());
            if (i % 2 != 0) {
                message.setLon(121.4703369140625);
                message.setLat(31.18930843952816);
            } else {
                message.setLon(120.33879507992552);
                message.setLat(31.512988562461025);
            }
            message.setEmissionStandards("1.23");
            Long id = new Random().nextLong();
            System.out.println(id);
            message.setId(id.toString());
            producer.send(new ProducerRecord("realtimeInfo-dev247",
                    JSON.toJSON(message).toString()));
            //Thread.sleep(5);
        }
    }
}
