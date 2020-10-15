package com.wxc.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;

        Connection connection = null;

        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        try {
            connection = connectionFactory.createConnection();

            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("MyQueue");

            MessageConsumer consumer = session.createConsumer(destination);


            TextMessage textMessage = (TextMessage) consumer.receive(10000);

            System.out.println("receiver received message:" + textMessage);

            System.out.println("receiver received message:" + textMessage.getText());


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
