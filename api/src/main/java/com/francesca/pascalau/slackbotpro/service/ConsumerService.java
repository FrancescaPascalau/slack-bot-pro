package com.francesca.pascalau.slackbotpro.service;

import com.francesca.pascalau.slackbotpro.data.CalendarEvent;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeoutException;

@Service
public class ConsumerService {

    private final static String QUEUE_NAME = "slack-bot-pro";
    private final static String HOST = "localhost";

    public CalendarEvent listenQueue() throws IOException, TimeoutException, ClassNotFoundException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        return consumeMessage(channel);
    }

    private CalendarEvent consumeMessage(Channel channel) throws IOException, ClassNotFoundException {
        final GetResponse getResponse = channel.basicGet(QUEUE_NAME, true);
        return convertDeliveryToCalendarEvent(getResponse.getBody());
    }

    //Deserialization of CalendarEvent objects
    private CalendarEvent convertDeliveryToCalendarEvent(byte[] body) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(body);
        ObjectInputStream obj = new ObjectInputStream(in);
        return (CalendarEvent) obj.readObject();
    }
}
