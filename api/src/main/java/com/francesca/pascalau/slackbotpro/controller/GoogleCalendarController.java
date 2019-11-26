package com.francesca.pascalau.slackbotpro.controller;

import com.francesca.pascalau.slackbotpro.data.CalendarEvent;
import com.francesca.pascalau.slackbotpro.service.GoogleCalendarService;
import com.francesca.pascalau.slackbotpro.service.ProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping
public class GoogleCalendarController {

    private GoogleCalendarService googleCalendarService;
    private ProducerService producer;

    public GoogleCalendarController() {
        this.googleCalendarService = new GoogleCalendarService();
        this.producer = new ProducerService();
    }

    @GetMapping("/postEvent")
    public void postCalendarEventsToQueue() throws IOException {
        var events = googleCalendarService.futureEvents();

        if (events.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (CalendarEvent event : events) {
                System.out.printf("(%s), %s, %s, %s, %s\n",
                        event.getId(),
                        event.getCreator(),
                        event.getDate().toString(),
                        event.getLocation(),
                        event.getDescription());

                producer.sendMessageToQueue(event);
            }
        }
    }
}