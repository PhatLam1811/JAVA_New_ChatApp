package com.example.java_new_chatapp.Firebase;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseManager {

    // region singleton
    private FirebaseManager() {
        this.m_firestore = FirebaseFirestore.getInstance();
    }

    private static FirebaseManager _instance;

    public static FirebaseManager getInstance() {
        if (_instance == null)
        {
            _instance = new FirebaseManager();
        }
        return _instance;
    }
    // endregion

    // fields
    private FirebaseFirestore m_firestore;

    // getters && setters
    public FirebaseFirestore getFirestore() {
        return this.m_firestore;
    }
}
