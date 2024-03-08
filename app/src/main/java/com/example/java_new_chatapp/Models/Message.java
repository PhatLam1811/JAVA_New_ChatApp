package com.example.java_new_chatapp.Models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Message {
    // fields
    private User m_sender;
    private User m_receiver;
    private String m_content;
    private Date m_dateTime;

    // getters && setters
    public User getSender() { return this.m_sender; }
    public User getReceiver() { return this.m_receiver; }
    public String getContent() { return this.m_content; }
    public Date getDateTime() { return this.m_dateTime; }

    public Message(User sender, User receiver, String content, Date dateTime) {
        this.m_sender = sender;
        this.m_receiver = receiver;
        this.m_content = content;
        this.m_dateTime = dateTime;
    }

    public static Message generateFakeMessage() {
        // faking sender && receiver
        User fakeSender = new User();
        User fakeReceiver = new User();

        // faking content
        String fakeContent = UUID.randomUUID().toString();

        // generate fake message
        Message fakeMessage = new Message(fakeSender, fakeReceiver, fakeContent, Calendar.getInstance().getTime());

        return fakeMessage;
    }
}
