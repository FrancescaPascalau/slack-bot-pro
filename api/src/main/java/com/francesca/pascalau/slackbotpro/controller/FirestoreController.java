package com.francesca.pascalau.slackbotpro.controller;

import com.francesca.pascalau.slackbotpro.service.FirestoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FirestoreController {
    private FirestoreService firestoreService;

    public FirestoreController(FirestoreService firestoreService) {
        this.firestoreService = firestoreService;
    }

    @GetMapping("/add")
    public void addEventToFirestore() {
        firestoreService.addEventToFirebase();
    }
}
