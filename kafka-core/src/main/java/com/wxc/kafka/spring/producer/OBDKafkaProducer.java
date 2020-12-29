package com.wxc.kafka.spring.producer;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author cAn
 */
@Component
public class OBDKafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(OBDKafkaProducer.class);
    public void send(String topic,Object object){
        String str = "";
        if(object instanceof String){
            str = object.toString();
            kafkaTemplate.send(topic, str);
        }else {
            str = JSON.toJSONString(object);
        }
        LOGGER.info("开始发送数据:"+str);
        kafkaTemplate.send(topic, str);
    }
}
