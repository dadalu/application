package com.wxc.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduceTopic {
    public static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    public static final String TOPIC_NAME ="sso.notify.risk";


    public static void main(String[] args) throws JMSException {
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
        //5、创建消息的生产者
        MessageProducer messageProducer = session.createProducer(topic);
        //6、使用消息生产者生产3条消息发送到MQ的队列里面
            //7、创建消息
            TextMessage textMessage = session.createTextMessage("{\"loginName\":\"xiaoming\",\"userName\":\"小明\",\"password\":1234,\"type\":\"ADD\",\"topic\":\"sso.notify.risk\"}");
            TextMessage textMessage1 = session.createTextMessage("{\"loginName\":\"xiaoming\",\"userName\":\"da小明\",\"password\":1234,\"type\":\"MODIFY\",\"topic\":\"sso.notify.risk\"}");
            TextMessage textMessage2 = session.createTextMessage("{\"loginName\":\"xiaoming\",\"userName\":\"小明\",\"password\":1234,\"type\":\"DELETE\",\"topic\":\"sso.notify.risk\"}");
            //8、通过messageProducer发送给mq
            messageProducer.send(textMessage);
            //messageProducer.send(textMessage1);
            messageProducer.send(textMessage2);



        //9、释放资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("TOPIC_MESSAGE 消息发送到MQ成功！！！");

    }
}
