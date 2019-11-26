package com.francesca.pascalau.slackbotpro.service;

import com.francesca.pascalau.slackbotpro.data.CalendarEvent;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
public class SlackBotService {

//    Old Webhook that is now disabled
//    private static final String slackUrl = "https://pagantis.slack.com/services/hooks/slackbot?token=sKhFiB4uTAoYe250cbiKcukQ&channel=slack-bot-events";

    private static final String slackUrl = "https://hooks.slack.com/services/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";

    public void sendMessageToChannel (CalendarEvent calendarEvent) throws IOException {
        String slackMessage = mapCalendarEventToSlackMessage(calendarEvent);

        configSlackMessage(slackMessage);
    }

    private void configSlackMessage(String slackMessage) {
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(slackUrl);

        JsonObject json = new JsonObject();
        json.add("text", new JsonPrimitive(slackMessage));
        StringEntity jsonBody = null;
        try {
            jsonBody = new StringEntity(json.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        httpPost.setEntity(jsonBody);
        httpPost.setHeader("Content-Type", "text/plain");
        try {
            client.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
