package com.francesca.pascalau.slackbotpro.config;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

public class FirestoreConfig {
    public Firestore firestore;

    public FirestoreConfig() {
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId(System.getenv("PROJECT_ID"))
                        .build();
        this.firestore = firestoreOptions.getService();
    }
}
