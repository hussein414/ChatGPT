package com.example.chatgpt.ui.view

import android.R
import android.animation.AnimatorInflater
import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatgpt.databinding.ActivityMainBinding
import com.example.chatgpt.ui.adpter.ChatBotAdapter
import com.example.chatgpt.ui.viewmodel.ChatBotViewModel
import com.example.chatgpt.utils.Constance


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var chatGptViewModel: ChatBotViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun bindViews() {
        chatGptViewModel = ViewModelProvider(this)[ChatBotViewModel::class.java]
        chatGptViewModel.messageList.observe(this) { messages ->
            val adapter = ChatBotAdapter(messages)
            binding.recyclerView.adapter = adapter
        }
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.sentButton.setOnClickListener {
            val question = binding.inputMessage.text.toString()
            chatGptViewModel.addToChat(
                question,
                Constance.SENT_BY_ME,
                chatGptViewModel.getCurrentTimestamp()
            )
            binding.inputMessage.setText("")
            chatGptViewModel.callAPi(question)
        }
        binding.close.setOnClickListener {
            finish()
        }

    }

}