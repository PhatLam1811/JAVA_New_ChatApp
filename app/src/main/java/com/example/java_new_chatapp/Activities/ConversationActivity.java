package com.example.java_new_chatapp.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.java_new_chatapp.databinding.ActivityConversationBinding;

public class ConversationActivity extends AppCompatActivity {
    // bindings
    private ActivityConversationBinding m_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init view bindings
        this.m_binding = ActivityConversationBinding.inflate(this.getLayoutInflater());
        setContentView(this.m_binding.getRoot());

        this.m_binding.btnSendMessage.setOnClickListener(this::onBtnSendClicked);
    }

    private void onBtnSendClicked(View btnSendMessage) {
        Log.d("[DEBUG]", "btn send message clicked!!!");
    }
}