package com.example.java_new_chatapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_new_chatapp.Models.Message;
import com.example.java_new_chatapp.R;
import com.example.java_new_chatapp.databinding.MsgViewHolderBinding;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    // properties
    private final ArrayList<Message> m_messages;

    public MessageAdapter(ArrayList<Message> messages) {
        this.m_messages = messages;
        // Log.d(AppDefines.Log.TAG_DEBUG, "init message adapter");
    }

    // region ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // view holder properties
        protected MsgViewHolderBinding m_binding;
        protected ConstraintSet m_constraintSet;
        protected Context m_context;

        // getters && setters
        protected LinearLayout getLayout() {
            return this.m_binding.msgViewHolderLayout;
        }

        protected TextView getSender() {
            return this.m_binding.txtViewSenderName;
        }

        protected TextView getMessageContent() {
            return this.m_binding.txtViewMessageContent;
        }

        protected TextView getMessageDate() {
            return this.m_binding.txtViewMessageDate;
        }

        public void setData(Message message, boolean isMsgSend) {
            this.getSender().setText(message.getSender().getName());
            this.getMessageContent().setText(message.getContent());
            this.getMessageDate().setText(message.getDateTime().toString());

            this.setMessageViewConstraint(isMsgSend);
            this.setMessageViewBackgroundColor(isMsgSend);
        }

        protected void setMessageViewConstraint(boolean isMsgSend) {
            this.m_constraintSet.clear(this.getLayout().getId(), ConstraintSet.RIGHT);
            this.m_constraintSet.clear(this.getLayout().getId(), ConstraintSet.LEFT);

            if (isMsgSend) {
                this.m_constraintSet.connect(
                        this.getLayout().getId(), ConstraintSet.RIGHT,
                        ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
            } else {
                this.m_constraintSet.connect(
                        this.getLayout().getId(), ConstraintSet.LEFT,
                        ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            }

            this.m_constraintSet.applyTo(this.m_binding.getRoot());
        }

        protected void setMessageViewBackgroundColor(boolean isMsgSend) {
            int colorId = isMsgSend ? R.color.purple_200 : R.color.teal_700;
            this.getLayout().setBackgroundColor(ContextCompat.getColor(this.m_context, colorId));
        }

        public ViewHolder(MsgViewHolderBinding binding, Context context) {
            super(binding.getRoot());

            // init view binding
            this.m_binding = binding;

            // init context
            this.m_context = context;

            // init constraint set
            this.m_constraintSet = new ConstraintSet();
            this.m_constraintSet.clone(this.m_binding.getRoot());

            // Log.d(AppDefines.Log.TAG_DEBUG, "init view holder");
        }
    }
    // endregion

    // region adapter logics
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MsgViewHolderBinding binding = MsgViewHolderBinding.inflate(layoutInflater, parent, false);

        // Log.d(AppDefines.Log.TAG_DEBUG, "on create view holder");

        return new MessageAdapter.ViewHolder(binding, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
    // endregion
}
