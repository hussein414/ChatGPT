package com.example.chatgpt.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatgpt.data.api.OpenAIClient
import com.example.chatgpt.data.model.CompletionRequest
import com.example.chatgpt.data.model.CompletionResponse
import com.example.chatgpt.data.model.Message
import com.example.chatgpt.utils.Constance.SENT_BY_BOT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatBotViewModel : ViewModel() {
    private val _messageList = MutableLiveData<MutableList<Message>>()
    val messageList: LiveData<MutableList<Message>> get() = _messageList

    init {
        _messageList.value = mutableListOf()
    }

     fun addToChat(message: String, sentBy: String, timeTamp: String) {
        val currentList = _messageList.value ?: mutableListOf()
        currentList.add(Message(message, sentBy, timeTamp))
        _messageList.postValue(currentList)
    }

    fun addResponse(response: String) {
        _messageList.value?.removeAt(_messageList.value?.size?.minus(1) ?: 0)
        addToChat(response, SENT_BY_BOT, getCurrentTimestamp())
    }

    fun callAPi(question: String) {
        addToChat("typing...", SENT_BY_BOT, getCurrentTimestamp())
        val completionRequest = CompletionRequest(
            max_tokens = 4000,
            model = "text-davinci-003",
            prompt = question
        )
        viewModelScope.launch {
            try {
                val response = OpenAIClient.apiService.getCompletions(completionRequest)
                handleApiService(response)
            } catch (e: SocketTimeoutException) {
                addResponse("timeout$e")
            }
        }
    }

    private suspend fun handleApiService(response: Response<CompletionResponse>) {
        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                response.body()?.let { completionResponse ->
                    val result = completionResponse.choices.firstOrNull()?.text
                    if (result != null) {
                        addResponse(result.trim())
                    } else {
                        addResponse("No Choices Found")
                    }
                }
            } else {
                addResponse("Failed to get Response${response.errorBody()}")
            }
        }
    }


     fun getCurrentTimestamp(): String {
        return SimpleDateFormat("hh mm a", Locale.getDefault()).format(Date())
    }
}