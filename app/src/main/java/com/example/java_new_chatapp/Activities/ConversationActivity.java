package com.example.java_new_chatapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.java_new_chatapp.Adapters.MessageAdapter;
import com.example.java_new_chatapp.Models.Message;
import com.example.java_new_chatapp.Models.User;
import com.example.java_new_chatapp.Utils.AppDefines;
import com.example.java_new_chatapp.databinding.ActivityConversationBinding;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConversationActivity extends AppCompatActivity {
    // bindings
    protected ActivityConversationBinding m_binding;
    protected MessageAdapter m_messageAdapter;
    protected Button m_btnSendMessage;
    protected EditText m_editTxtMessage;

    // participants
    protected ArrayList<User> m_participants;
    protected int m_appUserIndex;

    // messages
    protected  ArrayList<Message> m_messages;

    // firestore
    protected FirebaseFirestore m_firestoreDb;
    protected CollectionReference m_conversationCollection;

    // region getters && setters
    public User getAppUser() {
        return this.m_participants.get(this.m_appUserIndex);
    }

    public User getPartner() {
        int partnerIndex = (this.m_appUserIndex + 1) % this.m_participants.size();
        return this.m_participants.get(partnerIndex);
    }
    // endregion

    // region activity events
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init view bindings
        this.m_binding = ActivityConversationBinding.inflate(this.getLayoutInflater());
        setContentView(this.m_binding.getRoot());

        // button send message
        this.m_btnSendMessage = this.m_binding.btnSendMessage;
        this.m_btnSendMessage.setOnClickListener(this::onBtnSendClicked);

        // message text box
        this.m_editTxtMessage = this.m_binding.editTxtMessage;

        // fake conversation participants
        User fakeAppUser = User.getFakeAppUser();
        User fakePartner = User.getFakePartner();

        // init conversation participants
        this.m_participants = new ArrayList<>();
        this.m_participants.add(fakeAppUser);
        this.m_participants.add(fakePartner);
        this.m_appUserIndex = 0;

        // init conversation messages
        this.m_messages = new ArrayList<>();

        // message adapter
        this.m_messageAdapter = new MessageAdapter(this.m_messages);
        this.m_binding.recyclerViewMessages.setAdapter(this.m_messageAdapter);

        // init firestore
        this.m_firestoreDb = FirebaseFirestore.getInstance();
        this.m_conversationCollection = this.m_firestoreDb.collection("Messages");

        // load all messages
        this.m_conversationCollection
                .get()
                .addOnCompleteListener(this::onLoadAllMessagesCompleted);
    }
    // endregion

    // region callbacks
    protected void onBtnSendClicked(View btnSendMessage) {
        android.util.Log.d(AppDefines.Log.TAG_DEBUG, "btn send message clicked!!!");
        android.util.Log.d(AppDefines.Log.TAG_DEBUG, "message: " + this.m_editTxtMessage.getText());

        // create new message object
        Message newMessage = new Message
                (
                    this.getAppUser(),
                    this.getPartner(),
                    this.m_editTxtMessage.getText().toString(),
                    Calendar.getInstance().getTime()
                );

        // send new message
        this.m_conversationCollection
                .add(newMessage)
                .addOnCompleteListener(this::onSendMessageCompleted);

        // update local messages UI
        this.updateLocalMessagesList(newMessage);
    }

    protected void onSendMessageCompleted(Task<DocumentReference> docRefTask) {
        android.util.Log.d(AppDefines.Log.TAG_DEBUG, "send new message completed!!");
    }

    protected void onLoadAllMessagesCompleted(Task<QuerySnapshot> querySnapshotTask) {
        if (!querySnapshotTask.isSuccessful() || querySnapshotTask.isCanceled())
        {
            return;
        }

        QuerySnapshot querySnapshot = querySnapshotTask.getResult();
        List<DocumentSnapshot> docSnaps = querySnapshot.getDocuments();

        for (DocumentSnapshot doc : docSnaps)
        {
            Message message =doc.toObject(Message.class);
            this.addMessages(message);
        }

        this.m_messageAdapter.notifyItemRangeChanged(0, this.m_messages.size());
    }
    //endregion

    public void addMessages(Message message) {
        this.m_messages.add(message);
        this.m_messages.sort(Comparator.comparing(Message::getDateTime));
    }

    protected void updateLocalMessagesList(Message newMessage) {
        if (newMessage == null)
        {
            return;
        }

        this.addMessages(newMessage);

        this.m_messageAdapter.notifyItemRangeChanged(0, this.m_messages.size());
    }
}