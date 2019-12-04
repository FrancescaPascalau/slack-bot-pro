package com.francesca.pascalau.slackbotpro;

import com.francesca.pascalau.slackbotpro.entities.CalendarEvent;
import com.francesca.pascalau.slackbotpro.service.SlackBotService;
import com.google.api.client.util.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class SlackBotServiceShould {

    @Autowired
    private SlackBotService slackBotService;

    private CalendarEvent calendarEvent = CalendarEvent.builder()
            .id(UUID.randomUUID().toString())
            .description("description")
            .creator("F. Pascalau")
            .date(DateTime.parseRfc3339("2019-04-11T08:30:00.0000004Z"))
            .location("Barcelona, Da Vinci")
            .build();

    @Test
    public void send_message_to_slack_channel_with_CalendarEvent_information() {
        slackBotService.sendMessageToChannel(calendarEvent);
    }
}
