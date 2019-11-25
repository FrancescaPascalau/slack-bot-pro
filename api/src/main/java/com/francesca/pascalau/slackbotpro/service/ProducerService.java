package com.francesca.pascalau.slackbotpro.service;

import com.francesca.pascalau.slackbotpro.data.CalendarEvent;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeoutException;

@Service
public class ProducerService {

    private final static String QUEUE_NAME = "slack-bot-pro";
    private final static String HOST = "localhost";
    private final static String USERNAME = "guest";
    private final static String PASSWORD = "guest";

    public void sendMessageToQueue(CalendarEvent event) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        publishMessage(channel, event);
    }

    //Serializing CalendarEvent objects
    private void publishMessage(Channel channel, CalendarEvent event) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(event);

        channel.basicPublish("", QUEUE_NAME, null, outputStream.toByteArray());
    }
}
