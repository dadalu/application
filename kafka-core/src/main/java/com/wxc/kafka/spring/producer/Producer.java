package com.wxc.kafka.spring.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

public class Producer {
    @Test
    public void producerTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-kafka-producer.xml");
        KafkaTemplate kafkaTemplate = (KafkaTemplate) applicationContext.getBean("kafkaTemplate");
        kafkaTemplate.send("kafka-test-topic","valalalal");
        kafkaTemplate.send(new ProducerRecord<String, String>("kafka-test-topic","key","map.toString())"));
        ListenableFuture<SendResult<String, String>> listenableFuture =  kafkaTemplate.sendDefault("kafka-test-topic","valalalal");
        //发送成功回调
        SuccessCallback<SendResult<String, String>> successCallback = new SuccessCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                //成功业务逻辑
                System.out.println("onSuccess");
            }
        };
        //发送失败回调
        FailureCallback failureCallback = new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                //失败业务逻辑
                System.out.println("onFailure");
            }
        };
        listenableFuture.addCallback(successCallback, failureCallback);
    }
}
