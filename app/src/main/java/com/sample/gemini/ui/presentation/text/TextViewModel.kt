package com.sample.gemini.ui.presentation.text

import android.util.Log
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

    private val _loader: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loader get() = _loader

    private val _textResponse: MutableStateFlow<String> = MutableStateFlow("")
    val textResponse get() = _textResponse

    fun fetchText(text: String) {
        viewModelScope.launch {
            try {
                _textResponse.value = ""
                _loader.value = true
                generativeModel.generateContentStream(text).collect{
                    Log.d(">>",it.text.toString())
                    _loader.value = false
                    _textResponse.emit(_textResponse.value  + it.text )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}