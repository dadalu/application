package com.wxc.kafka;

import com.wxc.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kafka1")
public class KafkaController {
    private final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @GetMapping("/send/{input}")
    public void sendFoo(@PathVariable String input) {

        this.template.send("topic_input", input);
    }
    @KafkaListener(id = "webGroup", topics = "topic_input")
    public void listen(String input) {
        logger.info("input value: {}" , input);
    }
}
