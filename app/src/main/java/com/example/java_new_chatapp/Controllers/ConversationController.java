package com.example.java_new_chatapp.Controllers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.java_new_chatapp.Models.Message;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ConversationController {
    // firestore
    private FirebaseFirestore m_firestore;
    private final String _messageCollection = "Messages";

    public void init() {
        this.m_firestore = FirebaseFirestore.getInstance();
    }

    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> messages = new ArrayList<Message>();

        this.m_firestore
                .collection(_messageCollection)
                .get()
                .addOnSuccessListener(snapshots -> {
                    Log.d("[DEBUG]", snapshots.getDocuments().toString());
                })
                .addOnFailureListener(error -> Log.d("[DEBUG]", Objects.requireNonNull(error.getMessage())));

        return messages;
    }
}
