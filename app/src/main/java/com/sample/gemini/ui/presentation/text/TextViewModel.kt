package com.sample.gemini.ui.presentation.text

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.sample.gemini.di.GeminiPro
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TextViewModel @Inject constructor(@GeminiPro val generativeModel: GenerativeModel) :
    ViewModel() {

    private val _textInput: MutableStateFlow<String> = MutableStateFlow("")
    val textInput get() = _textInput

    private val _loader: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loader get() = _loader

    private val _textResponse: MutableStateFlow<String> = MutableStateFlow("")
    val textResponse get() = _textResponse

    fun setTextInput(textInput : String){
        _textInput.value = textInput
    }
    fun fetchText(text: String) {
        viewModelScope.launch {
            try {
                _loader.value = true
                val response = generativeModel.generateContent(text)
                _textResponse.value = response.text ?: ""
                _loader.value = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}