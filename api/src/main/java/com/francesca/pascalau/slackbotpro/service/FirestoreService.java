package com.francesca.pascalau.slackbotpro.service;

import com.francesca.pascalau.slackbotpro.config.FirestoreConfig;
import com.francesca.pascalau.slackbotpro.entities.EventDAO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {
    public FirestoreConfig firestoreConfig;

    public void addEventToFirebase() {
        EventDAO event = EventDAO.builder()
                .id(UUID.randomUUID().toString())
                .name("Test Event")
                .build();

        ApiFuture<WriteResult> addEvent = firestoreConfig
                .firestore
                .collection("calendar-events")
                .document("events")
                .set(event);

        try {
            System.out.println("Update time : " + addEvent.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
