package com.example.carol.bullyalert.chat;

import java.util.Date;

public class FirebaseMessage
{
    private String messageText;
    private String messageUser;
    private long messageTime;

    public FirebaseMessage(String messageText, String messageUser)
    {
        this.messageText = messageText;
        this.messageUser = messageUser;
        messageTime = new Date().getTime();
    }

    public FirebaseMessage()
    {

    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }
}
