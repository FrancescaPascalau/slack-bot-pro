package com.francesca.pascalau.slackbotpro.entities;

import com.google.api.client.util.DateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SlackMessage {
    private String text;
    private String channel;
    private DateTime sendAt;
}
