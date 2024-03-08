package com.example.java_new_chatapp.Controllers;

import android.util.Log;

import com.example.java_new_chatapp.Models.Message;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConversationController {
    // firestore
    private FirebaseFirestore m_firestore;
    private final String _messageCollection = "Messages";

    public void init() {
        this.m_firestore = FirebaseFirestore.getInstance();
    }

    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> messages = new ArrayList<>();

        this.m_firestore
                .collection(_messageCollection)
                .get()
                .addOnSuccessListener(snapshots -> {
                    List<DocumentSnapshot> documents = snapshots.getDocuments();
                    for (DocumentSnapshot doc : documents) {
                        Message msg = doc.toObject(Message.class);
                      if (msg == null)
                      {
                          Log.d("[DEBUG]", "null msg???");
                          continue;
                      }

                        Log.d("[DEBUG]", "Sender: " + msg.getSender().getName());
                        Log.d("[DEBUG]", "Receiver: " + msg.getReceiver().getName());
                        Log.d("[DEBUG]", "Content: " + msg.getContent());
                        Log.d("[DEBUG]", "Date: " + msg.getDateTime());
                    }
                })
                .addOnFailureListener(error -> Log.d("[DEBUG]", Objects.requireNonNull(error.getMessage())));

        return messages;
    }
}
