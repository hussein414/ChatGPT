package com.example.chatgpt.ui.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.chatgpt.R
import com.example.chatgpt.data.model.Message
import com.example.chatgpt.ui.adpter.ui.ChatBotViewHolder
import com.example.chatgpt.utils.Constance


class ChatBotAdapter(private val messageList: List<Message>) : Adapter<ChatBotViewHolder>() {

    companion object {
        const val SENT_BY_ME = 0
        const val SENT_BY_BOT = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatBotViewHolder {
        return when (viewType) {
            SENT_BY_ME -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_chat, parent, false)
                ChatBotViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_bot_chat, parent, false)
                ChatBotViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ChatBotViewHolder, position: Int) {
        holder.bindViews(messageList[position])
    }

    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]
        return when (message.sentBy) {
            Constance.SENT_BY_ME -> SENT_BY_ME
            else -> SENT_BY_BOT
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

}