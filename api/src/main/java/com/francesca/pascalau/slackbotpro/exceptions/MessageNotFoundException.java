package com.francesca.pascalau.slackbotpro.exceptions;

public class MessageNotFoundException extends Exception{
    public MessageNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
