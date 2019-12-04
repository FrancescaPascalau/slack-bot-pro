package com.francesca.pascalau.slackbotpro;

import com.francesca.pascalau.slackbotpro.entities.CalendarEvent;
import com.francesca.pascalau.slackbotpro.service.GoogleCalendarService;
import com.google.api.client.util.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class GoogleCalendarServiceShould {

    private static final CalendarEvent EVENT = CalendarEvent.builder()
            .id(UUID.randomUUID().toString())
            .date(DateTime.parseRfc3339("2019-04-11T08:30:00.0000004Z"))
            .location("Da Vinci")
            .creator("F. Pascalau")
            .description("Very important meeting")
            .build();

    @Mock
    private Calendar calendar;

    @InjectMocks
    private GoogleCalendarService googleCalendarService;

    @Test
    public void read_CalendarEvents_then_map_them_to_CalendarEvents_entities() {
        List<CalendarEvent> events = googleCalendarService.futureEvents();

        assertTrue(events.containsAll(googleCalendarService.futureEvents()));
    }
}