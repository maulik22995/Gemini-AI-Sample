package com.sample.gemini.ui.presentation.chat

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sample.gemini.R
import com.sample.gemini.ui.presentation.component.AppHeader
import com.sample.gemini.ui.presentation.component.ChatBoxView
import com.sample.gemini.ui.theme.Purple40

@Composable
fun Chat(navController: NavController = rememberNavController()) {
    val viewModel: ChatViewModel = hiltViewModel()
    val chatList = viewModel.chatList.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(10.dp, 10.dp)
    ) {
        AppHeader(onLeftBtnPress = { navController.popBackStack() })
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                reverseLayout = true
            ) {
                val list = chatList.value.reversed()
                Log.d(">>", list.toString())
                items(list.size) { i ->
                    val chat = list[i]
                    val isRightLayout = chat.type == ChatViewModel.ChatType.USER
                    val mainModifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(horizontal = 10.dp, vertical = 15.dp)

                    Column(
                        modifier = mainModifier.align(Alignment.End),
                        horizontalAlignment = if (isRightLayout) AbsoluteAlignment.Right else AbsoluteAlignment.Left
                    ) {
                        if (chat.type == ChatViewModel.ChatType.Loading) {
                            Loader()
                        } else {
                            Text(
                                text = chat.message.trim(),
                                color = if (isRightLayout) Purple40 else Color.White,
                                fontWeight = if (isRightLayout) FontWeight.Bold else FontWeight.Normal,
                                modifier = Modifier
                                    .widthIn(0.dp,300.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(if (isRightLayout) Color.White else Purple40)
                                    .padding(10.dp)
                            )
                        }

                    }
                }
            }
            ChatBoxView {
                viewModel.fetchText(it)
            }
        }
    }
}

@Composable
fun Loader() {
    LinearProgressIndicator()
}