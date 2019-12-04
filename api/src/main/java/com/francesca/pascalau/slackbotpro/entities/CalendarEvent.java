package com.francesca.pascalau.slackbotpro.entities;

import com.google.api.client.util.DateTime;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CalendarEvent implements Serializable {
    String id;
    DateTime date;
    String location;
    String creator;
    String description;
}
