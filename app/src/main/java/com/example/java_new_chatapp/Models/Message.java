package com.example.java_new_chatapp.Models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class Message {
    // fields
    private User m_sender;
    private User m_receiver;
    private String m_content;
    private LocalDateTime m_dateTime;

    // getters && setters
    public User getSender() { return this.m_sender; }
    public User getReceiver() { return this.m_receiver; }
    public String getContent() { return this.m_content; }
    public LocalDateTime getDateTime() { return this.m_dateTime; }

    public Message(User sender, User receiver, String content, LocalDateTime dateTime) {
        this.m_sender = sender;
        this.m_receiver = receiver;
        this.m_content = content;
        this.m_dateTime = dateTime;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Message generateFakeMessage() {
        // faking sender && receiver
        User fakeSender = new User();
        User fakeReceiver = new User();

        // faking content
        String fakeContent = UUID.randomUUID().toString();

        // generate fake message
        Message fakeMessage = new Message(fakeSender, fakeReceiver, fakeContent, LocalDateTime.now(Clock.systemDefaultZone()));

        return fakeMessage;
    }
}
