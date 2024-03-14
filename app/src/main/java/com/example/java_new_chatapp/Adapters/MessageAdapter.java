package com.example.java_new_chatapp.Adapters;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_new_chatapp.Models.Message;
import com.example.java_new_chatapp.Utils.AppDefines;
import com.example.java_new_chatapp.databinding.MessageViewHolderBinding;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    // properties
    private ArrayList<Message> m_messages;

    public MessageAdapter(ArrayList<Message> messages) {
        this.m_messages = messages;
        // Log.d(AppDefines.Log.TAG_DEBUG, "init message adapter");
    }

    // region ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // view holder properties
        protected MessageViewHolderBinding m_binding;

        // getters && setters
        protected TextView getSender()
        {
            return this.m_binding.txtViewSenderName;
        }
        protected TextView getMessageContent()
        {
            return this.m_binding.txtViewMessageContent;
        }
        protected TextView getMessageDate()
        {
            return this.m_binding.txtViewMessageDate;
        }
        public void setData(Message message) {
            this.getSender().setText(message.getSender().getName());
            this.getMessageContent().setText(message.getContent());
            this.getMessageDate().setText(message.getDateTime().toString());
        }


        public ViewHolder(MessageViewHolderBinding binding) {
            super(binding.getRoot());
            this.m_binding = binding;
            // Log.d(AppDefines.Log.TAG_DEBUG, "init view holder");
        }
    }
    // endregion

    // region adapter logics
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MessageViewHolderBinding binding = MessageViewHolderBinding.inflate(layoutInflater, parent, false);

        // Log.d(AppDefines.Log.TAG_DEBUG, "on create view holder");

        return new MessageAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (this.m_messages == null || this.m_messages.isEmpty())
        {
            return;
        }

        // Log.d(AppDefines.Log.TAG_DEBUG, "on bind view holder");

        Message bindingMessage = this.m_messages.get(position);

        holder.setData(bindingMessage);
    }

    @Override
    public int getItemCount() {
        // Log.d(AppDefines.Log.TAG_DEBUG, "get item count");
        return this.m_messages.size();
    }
    // endregion
}
