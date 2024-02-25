package com.sample.gemini.ui.presentation.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.sample.gemini.di.GeminiPro
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(@GeminiPro val generativeModel: GenerativeModel) :
    ViewModel() {

    private val _chatList: MutableStateFlow<List<Chat>> = MutableStateFlow(listOf())
    val chatList get() = _chatList

    fun fetchText(text: String) {
        viewModelScope.launch {
            try {
                addTextToList(text)
                val chat = generativeModel.startChat()
                val response = chat.sendMessage(text)
                Log.d(">> chat response", response.text ?: " ")
                removeLoading()
                _chatList.update {
                    it + Chat(ChatType.MODEL, response.text ?: "")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                removeLoading()
            }
        }
    }

    private fun addTextToList(msg: String) {
        _chatList.update {
            it + Chat(ChatType.USER, msg) + Chat(ChatType.Loading)
        }
    }

    private fun removeLoading() {
        if (_chatList.value.last().type == ChatType.Loading) {
            _chatList.update { it.dropLast(1) }
        }
    }

    data class Chat(
        val type: ChatType,
        val message: String = "",
    )

    enum class ChatType {
        USER,
        MODEL,
        Loading
    }
}