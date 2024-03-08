package com.example.java_new_chatapp.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.java_new_chatapp.Controllers.ConversationController;
import com.example.java_new_chatapp.Models.Message;
import com.example.java_new_chatapp.Models.User;
import com.example.java_new_chatapp.databinding.ActivityConversationBinding;

import java.util.ArrayList;

public class ConversationActivity extends AppCompatActivity {
    // bindings
    private ActivityConversationBinding m_binding;

    // controller
    private ConversationController m_controller;

    // users
    private ArrayList<User> m_participants;
    private int m_userIndex;

    // messages
    private ArrayList<Message> m_messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init view binding
        this.m_binding =ActivityConversationBinding.inflate(this.getLayoutInflater());
        setContentView(this.m_binding.getRoot());

        // init controller
        this.m_controller = new ConversationController();
        this.m_controller.init();

        // init users
        this.m_participants = new ArrayList<User>();
        this.m_userIndex = -1;

        // load all messages of the conversation
        // TO-DO: only load recent messages (10-15) instead of loading all
        this.m_messages = this.m_controller.getAllMessages();
    }
}