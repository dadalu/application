package com.wxc.kafka.spring.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

public class KafkaSendMessageServiceImpl implements MessageListener<String,String> {

@Override
public void onMessage(ConsumerRecord<String, String> data) {
        //根据不同主题，消费
        System.out.println("========");
        if("topic1".equals(data.topic())){
        //逻辑1
        System.out.println(data.value()+"被消费");
        }else if("topic2".equals(data.topic())){
        //逻辑2
        System.out.println(data.value()+"主题2 被消费");
        }
        }
}
