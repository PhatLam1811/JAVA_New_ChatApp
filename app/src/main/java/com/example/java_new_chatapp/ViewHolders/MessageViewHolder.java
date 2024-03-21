package com.example.java_new_chatapp.ViewHolders;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_new_chatapp.Models.Message;
import com.example.java_new_chatapp.R;
import com.example.java_new_chatapp.databinding.MsgViewHolderBinding;

public class MessageViewHolder {
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
}
