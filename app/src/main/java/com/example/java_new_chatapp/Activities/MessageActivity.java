package com.example.java_new_chatapp.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.java_new_chatapp.R;
import com.example.java_new_chatapp.databinding.ActivityMessageBinding;

public class MessageActivity extends AppCompatActivity {
    private ActivityMessageBinding m_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init view binding
        this.m_binding =ActivityMessageBinding.inflate(this.getLayoutInflater());
        setContentView(this.m_binding.getRoot());
    }
}