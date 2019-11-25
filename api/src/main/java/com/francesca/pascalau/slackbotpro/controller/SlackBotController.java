package com.francesca.pascalau.slackbotpro.controller;

import com.francesca.pascalau.slackbotpro.data.CalendarEvent;
import com.francesca.pascalau.slackbotpro.service.ConsumerService;
import com.francesca.pascalau.slackbotpro.service.SlackBotService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public String readCalendarEventsFromQueue() throws IOException, ClassNotFoundException {
        if(true)
            throw new RuntimeException();

        final var calendarEvent = consumer.listenQueue();
        sendMessage(calendarEvent);
        return calendarEvent.toString();
    }

    private void sendMessage(CalendarEvent calendarEvent) {
        slackBotService.sendMessageToChannel(calendarEvent);
    }
}
