package com.sample.gemini.ui.presentation.Text

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.sample.gemini.di.GeminiPro
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TextViewModel @Inject constructor(@GeminiPro val generativeModel: GenerativeModel) :
    ViewModel() {

    fun fetchText(text: String) {
        viewModelScope.launch {
            try {
                val response = generativeModel.generateContent(text)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}