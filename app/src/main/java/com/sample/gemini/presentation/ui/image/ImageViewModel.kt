package com.sample.gemini.presentation.ui.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.sample.gemini.di.GeminiProVision
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(@GeminiProVision val generativeModel: GenerativeModel) :
    ViewModel() {

    private val _selectedImgBitmap: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val selectedImgBitmap get() = _selectedImgBitmap

    private val _loader: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loader get() = _loader

    private val _textResponse: MutableStateFlow<String> = MutableStateFlow("")
    val textResponse get() = _textResponse


    fun prepareImageBitmap(context: Context, path: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            var inputStream: InputStream? = null
            try {
                inputStream = context.contentResolver.openInputStream(path)
                val bm = BitmapFactory.decodeStream(inputStream)
                withContext(Dispatchers.Main) {
                    _selectedImgBitmap.value = bm
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } finally {
                try {
                    inputStream?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun fetchText(searchStr: String) {
        viewModelScope.launch {
            try {
                _loader.value = true
                val inputContent = content {
                    selectedImgBitmap.value?.let { image(it) }
                    text(searchStr)
                }
                val response = generativeModel.generateContent(inputContent)
                _textResponse.value = response.text ?: ""
                _loader.value = false
            } catch (e: Exception) {
                _loader.value = false
                e.printStackTrace()
            }
        }
    }
}