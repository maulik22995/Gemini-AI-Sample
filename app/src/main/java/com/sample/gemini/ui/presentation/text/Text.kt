package com.sample.gemini.ui.presentation.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sample.gemini.ui.presentation.component.AppHeader
import com.sample.gemini.ui.presentation.component.ChatBoxView

@Composable
fun Text(navController: NavController = rememberNavController()) {
    val viewModel: TextViewModel = hiltViewModel()
    val isLoading = viewModel.loader.collectAsStateWithLifecycle()
    val responseText = viewModel.textResponse.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(10.dp),
    ) {
        AppHeader(onLeftBtnPress = { navController.popBackStack() })
        ChatBoxView {
            viewModel.fetchText(it)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())

        ) {
            if (isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                androidx.compose.material3.Text(responseText.value, color = Color.White)
            }
        }
    }
}