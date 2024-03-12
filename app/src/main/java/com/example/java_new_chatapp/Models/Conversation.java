package com.example.java_new_chatapp.Models;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class Conversation implements OnCompleteListener {
    private User m_User;
    private User m_Partner;
    private List<Message> m_Messages;

    @Override
    public void onComplete(@NonNull Task task) {

    }
}
