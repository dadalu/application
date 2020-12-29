package com.wxc;

import com.wxc.kafka.java.producer.RealtimeMessage;
import com.wxc.kafka.spring.producer.OBDKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class BaseproductApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BaseproductApplication.class, args);
    }
    @Autowired
    private OBDKafkaProducer producer;
    @Override
    public void run(String... args) throws Exception {
        RealtimeMessage message = new RealtimeMessage();
        message.setLicensePrefix("111");
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
        message.setLon(1.23);
        message.setLat(1.23);
        message.setEmissionStandards("1.23");
        while (true) {
            Integer id = new Random().nextInt(1000000000) + 100000000;
            message.setId(id.toString());
/*            realtimeProcessor.process(message);
            realtimeProcessor.process(message);
            realtimeProcessor.process(message);
            realtimeProcessor.process(message);*/
            producer.send("realtimeInfo-dev",message);
            Thread.sleep(500);
        }
    }
}
