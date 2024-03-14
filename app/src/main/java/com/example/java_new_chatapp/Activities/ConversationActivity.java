package com.example.java_new_chatapp.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_new_chatapp.Adapters.MessageAdapter;
import com.example.java_new_chatapp.Models.Message;
import com.example.java_new_chatapp.Models.User;
import com.example.java_new_chatapp.Utils.AppDefines;
import com.example.java_new_chatapp.databinding.ActivityConversationBinding;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

public class ConversationActivity extends AppCompatActivity {
    // bindings
    protected ActivityConversationBinding m_binding;
    protected MessageAdapter m_messageAdapter;
    protected RecyclerView.SmoothScroller m_messagesSmoothScroller;

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

    public Button getBtnSendMessage() {
        return this.m_binding.btnSendMessage;
    }

    public EditText getEditTxtMessage() {
        return this.m_binding.editTxtMessage;
    }

    public RecyclerView getRecyclerViewMessage() {
        return this.m_binding.recyclerViewMessages;
    }

    public RecyclerView.SmoothScroller getMessagesSmoothScroller() {
        if (this.m_messagesSmoothScroller == null)
        {
            this.m_messagesSmoothScroller = new LinearSmoothScroller(this.getRecyclerViewMessage().getContext()) {
                // Override computeScrollVectorForPosition to customize scrolling direction
                @Override
                protected int getVerticalSnapPreference() {
                    return SNAP_TO_START; // Or SNAP_TO_END, SNAP_TO_ANY
                }
            };
        }
        return m_messagesSmoothScroller;
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
        this.getBtnSendMessage().setOnClickListener(this::onBtnSendClicked);

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
        this.m_messageAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                Log.d(AppDefines.Log.TAG_DEBUG, "on dataset changed");
                scrollToLatestMessage();
            }
        });

        // init firestore
        this.m_firestoreDb = FirebaseFirestore.getInstance();
        this.m_conversationCollection = this.m_firestoreDb.collection("Messages");
        this.m_conversationCollection.addSnapshotListener(this::onMessageDataUpdated);
    }
    // endregion

    // region callbacks
    protected void onBtnSendClicked(View btnSendMessage) {
        android.util.Log.d(AppDefines.Log.TAG_DEBUG, "btn send message clicked!!!");
        android.util.Log.d(AppDefines.Log.TAG_DEBUG, "message: " + this.getEditTxtMessage().getText());

        // create new message object
        Message newMessage = new Message
                (
                    this.getAppUser(),
                    this.getPartner(),
                    this.getEditTxtMessage().getText().toString(),
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

    private void onMessageDataUpdated(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
        if (e != null)
        {
            return;
        }

        boolean isDataChanged = false;

        for (DocumentChange change : queryDocumentSnapshots.getDocumentChanges())
        {
            DocumentSnapshot docSnap = change.getDocument();
            Message changeMessage = docSnap.toObject(Message.class);
            this.addMessages(changeMessage);
            isDataChanged = true;
        }

        if (isDataChanged)
        {
            this.m_messageAdapter.notifyItemRangeChanged(0, this.m_messages.size());
        }
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

    protected void scrollToLatestMessage() {
        int latestMessageIndex = this.m_messages.size() - 1;

        RecyclerView messageRecyclerView = this.getRecyclerViewMessage();
        RecyclerView.LayoutManager messageLayoutManager = messageRecyclerView.getLayoutManager();
        RecyclerView.SmoothScroller messageSmoothScroller = this.getMessagesSmoothScroller();

        messageSmoothScroller.setTargetPosition(latestMessageIndex);

        if (messageLayoutManager != null)
        {
            messageLayoutManager.startSmoothScroll(messageSmoothScroller);
            Log.d(AppDefines.Log.TAG_DEBUG, "should be able to scroll");
        }
    }
}