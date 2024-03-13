package com.example.java_new_chatapp.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_new_chatapp.Models.Message;
import com.example.java_new_chatapp.databinding.MessageViewHolderBinding;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    // properties
    private ArrayList<Message> m_messages;

    public MessageAdapter(ArrayList<Message> messages) {
        this.m_messages = messages;
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
        public void setData(Message message) { }


        public ViewHolder(MessageViewHolderBinding binding) {
            super(binding.getRoot());
            this.m_binding = binding;
        }
    }
    // endregion

    // region adapter logics
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MessageViewHolderBinding binding = MessageViewHolderBinding.inflate(layoutInflater, parent, false);
        return new MessageAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (this.m_messages == null || this.m_messages.isEmpty())
        {
            return;
        }

        Message bindingMessage = this.m_messages.get(position);

        holder.setData(bindingMessage);
    }

    @Override
    public int getItemCount() {
        return this.m_messages.size();
    }
    // endregion
}
