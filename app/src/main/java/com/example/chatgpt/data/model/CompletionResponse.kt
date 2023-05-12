package com.example.chatgpt.data.model

data class CompletionResponse(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val obj: String,
    val usage: Usage
)