package com.francesca.pascalau.slackbotpro.service;

import com.francesca.pascalau.slackbotpro.data.CalendarEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SlackBotService {

    private static final String slackUrl = "https://pagantis.slack.com/services/hooks/slackbot?token=sKhFiB4uTAoYe250cbiKcukQ&channel=slack-bot-events";

    public void sendMessageToChannel (CalendarEvent calendarEvent) throws IOException {
        String slackMessage = mapCalendarEventToSlackMessage(calendarEvent);

        configSlackMessage(slackMessage);
    }

    private void configSlackMessage(String slackMessage) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(slackUrl);

        StringEntity entity = new StringEntity(slackMessage);
        httpPost.setEntity(entity);

        client.execute(httpPost);
    }

    private String mapCalendarEventToSlackMessage(CalendarEvent calendarEvent) {
        String creator = StringUtils.substringBetween(calendarEvent.getCreator(), "\":\"", "\",\"");
        String rawDate = calendarEvent.getDate().toString();
        return "You have an event today created by " + creator + ".\n"
                            + "It will be held at " + rawDate.substring(0, 10)
                            + ", in " + calendarEvent.getLocation() + ".\n"
                            + "Event description: " + calendarEvent.getDescription();
    }
}
