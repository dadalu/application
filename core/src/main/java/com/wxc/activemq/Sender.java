package com.wxc.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Sender {


    public static void main(String[] args) {
        ConnectionFactory connectionFactory;

        Connection connection = null;

        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        try {
            connection = connectionFactory.createConnection();

            connection.start();

            /*

             * arg0:与事物有关，true表示最后提交，false表示自动提交

             * arg1:表示消息向中间件发送确认通知，这里采用的是自动通知的类型

             */

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("MyQueue");

            MessageProducer messageProducer = session.createProducer(destination);

            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);


            TextMessage textMessage = session.createTextMessage("hello,ActiveMQ");

            messageProducer.send(textMessage);


            session.close();

        } catch (JMSException e) {
            e.printStackTrace();

        } finally {
            try {
                if (connection != null)

                    connection.close();

                else

                    System.out.println("connection is null");

            } catch (JMSException e) {
                e.printStackTrace();

            }

        }


    }

}
