package com.example.chatgpt.data.model

data class CompletionRequest(
    val max_tokens: Int,
    val model: String,
    val prompt: String,
    val temperature: Float = 0f
)