package com.example.chatgpt.data.api

import com.example.chatgpt.data.model.CompletionRequest
import com.example.chatgpt.data.model.CompletionResponse
import com.example.chatgpt.utils.Constance.MY_API_KEY
import com.example.chatgpt.utils.Constance.END_POINT
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiService {
    @Headers("Authorization: Bearer $MY_API_KEY")
    @POST(END_POINT)
    suspend fun getCompletions(@Body completionRequest: CompletionRequest):Response<CompletionResponse>
}