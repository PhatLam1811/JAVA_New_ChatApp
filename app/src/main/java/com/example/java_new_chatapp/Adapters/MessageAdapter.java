package com.example.java_new_chatapp.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_new_chatapp.Models.Message;
import com.example.java_new_chatapp.ViewHolders.MessageViewHolder;
import com.example.java_new_chatapp.databinding.MsgViewHolderBinding;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder.ViewHolder> {
    // properties
    private final ArrayList<Message> m_messages;

    public MessageAdapter(ArrayList<Message> messages) {
        this.m_messages = messages;
        // Log.d(AppDefines.Log.TAG_DEBUG, "init message adapter");
    }

    @NonNull
    @Override
    public MessageViewHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MsgViewHolderBinding binding = MsgViewHolderBinding.inflate(layoutInflater, parent, false);

        // Log.d(AppDefines.Log.TAG_DEBUG, "on create view holder");

        return new MessageViewHolder.ViewHolder(binding, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder.ViewHolder  holder, int position) {
        if (this.m_messages == null || this.m_messages.isEmpty()) {
            return;
        }

        // Log.d(AppDefines.Log.TAG_DEBUG, "on bind view holder");

        Message bindingMessage = this.m_messages.get(position);

        boolean isMsgSend = position % 2 == 0;

        holder.setData(bindingMessage, isMsgSend);
    }

    @Override
    public int getItemCount() {
        // Log.d(AppDefines.Log.TAG_DEBUG, "get item count");
        return this.m_messages.size();
    }
}
