package com.bit.models;

import lombok.Data;

@Data
public class EmailMessageContent {

    private String emailAddress;

    private String senderName;

    private String messageContent;
}