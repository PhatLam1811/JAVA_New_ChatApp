package com.example.java_new_chatapp.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.java_new_chatapp.Firebase.FirebaseManager;
import com.example.java_new_chatapp.Models.Message;
import com.example.java_new_chatapp.databinding.ActivityMessageBinding;

public class MessageActivity extends AppCompatActivity {
    private ActivityMessageBinding m_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init view binding
        this.m_binding =ActivityMessageBinding.inflate(this.getLayoutInflater());
        setContentView(this.m_binding.getRoot());

        // generate first message
        Message firstMessage = Message.generateFakeMessage();

        // upload first message
        FirebaseManager.getInstance().getFirestore()
                .collection("Messages")
                .add(firstMessage)
                .addOnSuccessListener(p -> Log.d("[DEBUG]", "upload message successful!"))
                .addOnFailureListener(p -> Log.d("[DEBUG]", "upload message failed!"));
    }
}