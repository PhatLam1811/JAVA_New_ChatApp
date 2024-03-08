package com.example.java_new_chatapp.Models;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Message {
    // fields
    private User sender;
    private User receiver;
    private String content;
    private Date dateTime;

    // getters && setters
    public User getSender() { return this.sender; }
    public User getReceiver() { return this.receiver; }
    public String getContent() { return this.content; }
    public Date getDateTime() { return this.dateTime; }

    // constructors
    public Message() {
        this.sender = null;
        this.receiver = null;
        this.content = "";
        this.dateTime = null;
    }
    public Message(User sender, User receiver, String content, Date dateTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.dateTime = dateTime;
    }

    public static Message getFakeMessage() {
        // faking sender && receiver
        User fakeAppUser = User.getFakeAppUser();
        User fakePartner = User.getFakePartner();

        // faking content
        String fakeContent = UUID.randomUUID().toString();

        // generate fake message
        return new Message(fakeAppUser, fakePartner, fakeContent, Calendar.getInstance().getTime());
    }
}
