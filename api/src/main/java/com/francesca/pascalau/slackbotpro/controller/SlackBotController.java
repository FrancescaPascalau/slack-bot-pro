package com.francesca.pascalau.slackbotpro.controller;

import com.francesca.pascalau.slackbotpro.entities.CalendarEvent;
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
    public String readCalendarEventsFromQueue() {
        CalendarEvent calendarEvent = null;
        try {
            calendarEvent = consumer.listenQueue();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        sendMessage(calendarEvent);
        return calendarEvent.toString();
    }

    private void sendMessage(CalendarEvent calendarEvent) {
        slackBotService.sendMessageToChannel(calendarEvent);
    }
}
