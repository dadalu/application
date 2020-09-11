package com.wxc.rabbitmq.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //1.创建一个ConnectionFactory,并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.12.193.154");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        //2.通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        //3.通过connection创建一个channel
        Channel channel = connection.createChannel();
        //4.声明一个队列
        String queueName = "test001";
        channel.queueDeclare(queueName,true,false,false,null);
        //5.创建消费者
        QueueingConsumer qc = new QueueingConsumer(channel);
        //6.设置Channel
        channel.basicConsume(queueName,true,qc);
        //7.获取消息
        Delivery delivery = qc.nextDelivery();
        String msg = new String(delivery.getBody());
        System.err.println("消费端读到消息："+msg);


    }
}
