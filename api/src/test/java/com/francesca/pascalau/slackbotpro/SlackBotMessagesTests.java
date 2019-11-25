package com.francesca.pascalau.slackbotpro;

import com.francesca.pascalau.slackbotpro.data.CalendarEvent;
import com.francesca.pascalau.slackbotpro.service.SlackBotService;
import com.google.api.client.util.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;

public class SlackBotMessagesTests {

    private CalendarEvent calendarEvent = CalendarEvent.builder()
            .id(UUID.randomUUID().toString())
            .description("description")
            .creator("F. Pascalau")
            .date(DateTime.parseRfc3339("2019-04-11T08:30:00.0000004Z"))
            .location("Barcelona, Da Vinci")
            .build();

    @Autowired
    private SlackBotService slackBotService;

    @Test
    public void send_SlackMessage_with_CalendarEvent_information() throws IOException {
        slackBotService.sendMessageToChannel(calendarEvent);
    }
}
