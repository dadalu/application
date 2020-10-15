package com.wxc.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author houChen
 * @Description: 发布/订阅的消息消费者
 * @date 2020/7/13 14:32
 */
public class JmsConsumerTopic {

    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String TOPIC_NAME = "sso.notify.risk";

    public static void main(String[] args) throws JMSException, IOException {

        System.out.println("我是1号消费者");
        //1、创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2、通过连接工程获取connection
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3、创建会话
        //两个参数  1：事务  2：签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地
        Topic topic = session.createTopic(TOPIC_NAME);
        //5、创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);
        // 通过监听的方式来接收消息 ===》 有消息就进行消费，没有消息则继续等待
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (null != message && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("消费者接收到消息：" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.in.read();  // 等待 监听器进行监听
        messageConsumer.close();
        session.close();
        connection.close();
        System.out.println("消息消费成功！！！");
    }

}
