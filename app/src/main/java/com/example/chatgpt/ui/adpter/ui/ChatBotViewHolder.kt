package com.example.chatgpt.ui.adpter.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chatgpt.R
import com.example.chatgpt.data.model.Message
import com.example.chatgpt.utils.Constance.SENT_BY_ME

class ChatBotViewHolder( itemview:View):ViewHolder(itemview) {
    private val leftChatTextView: TextView? = itemView.findViewById(R.id.leftChatTextView)
    private val leftChatTimestamp: TextView? = itemView.findViewById(R.id.leftChatTimestamp)
    private val rightChatTextView: TextView? = itemView.findViewById(R.id.rightChatTextView)
    private val rightChatTimestamp: TextView? = itemView.findViewById(R.id.rightChatTimestamp)

    fun bindViews(message: Message){
        when (message.sentBy) {
           SENT_BY_ME -> {
                rightChatTextView?.text = message.message
                rightChatTimestamp?.text = message.timestamp
            }
            else -> {
                leftChatTextView?.text = message.message
                leftChatTimestamp?.text = message.timestamp
            }
        }
    }
}