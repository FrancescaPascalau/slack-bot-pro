package com.francesca.pascalau.slackbotpro.controller;

import com.francesca.pascalau.slackbotpro.service.ConsumerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String globalHandler(){
        return "Invalid request. Try again.";
    }

    @ExceptionHandler(ConsumerService.NoCalendarEventsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noMoreCalendarEventsHandler() {
        return "No calendar events scheduled.";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String globalHandler(Exception exception){
        System.out.println("The service could not process the request due to  " + exception.getMessage());
        return "Something unexpected happened, please try again later.";
    }
}