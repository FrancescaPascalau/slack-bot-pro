package com.francesca.pascalau.slackbotpro.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class EventDAO {
    @Id
    private String id;

    private String name;
}
