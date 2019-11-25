package com.francesca.pascalau.slackbotpro.controller;

import com.francesca.pascalau.slackbotpro.data.CalendarEvent;
import com.francesca.pascalau.slackbotpro.exceptions.MessageNotFoundException;
import com.francesca.pascalau.slackbotpro.service.ConsumerService;
import com.francesca.pascalau.slackbotpro.service.SlackBotService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping
public class SlackBotController {

    private SlackBotService slackBotService;
    private ConsumerService consumer;

    public SlackBotController (SlackBotService slackBotService, ConsumerService consumer) {
        this.slackBotService = slackBotService;
        this.consumer = consumer;
    }

    @GetMapping("/readEvent")
    public void readCalendarEventsFromQueue() throws IOException, TimeoutException, ClassNotFoundException, MessageNotFoundException {
        sendMessage(consumer.listenQueue());
    }

    private void sendMessage(CalendarEvent calendarEvent)throws IOException {
        slackBotService.sendMessageToChannel(calendarEvent);
    }
}
