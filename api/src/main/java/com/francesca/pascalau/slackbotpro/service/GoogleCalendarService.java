package com.francesca.pascalau.slackbotpro.service;

import com.francesca.pascalau.slackbotpro.config.GoogleCalendarConfig;
import com.francesca.pascalau.slackbotpro.entities.CalendarEvent;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleCalendarService {
    private Calendar calendar;

    public GoogleCalendarService() {
        this.calendar = new GoogleCalendarConfig().calendarService();
    }

    public List<CalendarEvent> futureEvents() {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = null;
        try {
            events = calendar.events().list("primary")
                        .setMaxResults(10)
                        .setTimeMin(now)
                        .setOrderBy("startTime")
                        .setSingleEvents(true)
                        .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapEvents(events.getItems());
    }

    private List<CalendarEvent> mapEvents(List<Event> events) {
        List<CalendarEvent> calendarEvents = new ArrayList<>();
        for(Event event : events){
            calendarEvents.add(mapCalendarEvent(event));
        }
        return calendarEvents;
    }

    private CalendarEvent mapCalendarEvent(Event event){
        return CalendarEvent.builder()
                .id(event.getId())
                .date(event.getStart().getDateTime())
                .location(event.getLocation())
                .creator(event.getCreator().toString())
                .description(event.getDescription())
                .build();
    }
}
