package com.example.java_new_chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.java_new_chatapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding m_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialized view binding
        this.m_binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.m_binding.getRoot());

        this.m_binding.textLog.setText("No more hello world!");
    }
}