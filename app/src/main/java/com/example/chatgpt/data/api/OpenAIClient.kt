package com.example.chatgpt.data.api

import com.example.chatgpt.utils.Constance.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenAIClient {
    private val httpClient = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(httpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val apiService: OpenAiService = retrofit.create(OpenAiService::class.java)
}