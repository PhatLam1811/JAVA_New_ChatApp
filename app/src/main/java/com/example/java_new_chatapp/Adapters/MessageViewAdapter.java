package com.example.java_new_chatapp.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_new_chatapp.Models.Message;

import java.util.ArrayList;

public class MessageViewAdapter extends RecyclerView.Adapter<MessageViewAdapter.ViewHolder> {
    // properties
    private ArrayList<Message> m_messages;

    // region ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    // endregion

    // region adapter logics
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    // endregion
}
