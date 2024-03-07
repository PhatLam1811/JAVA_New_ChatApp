package com.example.java_new_chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

// binding
import com.example.java_new_chatapp.databinding.ActivityMainBinding;
import com.google.firebase.firestore.FirebaseFirestore;

// firestore

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding m_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialized view binding
        this.m_binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.m_binding.getRoot());

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    }
}