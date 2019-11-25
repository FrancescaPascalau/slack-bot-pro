package com.francesca.pascalau.slackbotpro.controller;

import com.francesca.pascalau.slackbotpro.data.CalendarEvent;
import com.francesca.pascalau.slackbotpro.service.ConsumerService;
import com.francesca.pascalau.slackbotpro.service.ProducerService;
import com.google.api.client.util.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping
public class RabbitMqController {

    private ProducerService producer;
    private ConsumerService consumer;


    public RabbitMqController(ProducerService producer, ConsumerService consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @GetMapping("/sendMessage")
    public void sendMessage() throws IOException, TimeoutException {
        producer.sendMessageToQueue(CalendarEvent.builder()
                .id(UUID.randomUUID().toString())
                .date(DateTime.parseRfc3339("2019-04-11T08:30:00.0000004Z"))
                .location("Da Vinci")
                .creator("F. Pascalau")
                .description("Very important meeting")
                .build());
    }

    @GetMapping("/readMessage")
    public void readMessage() throws IOException, TimeoutException, ClassNotFoundException {
        consumer.listenQueue();
    }
}
