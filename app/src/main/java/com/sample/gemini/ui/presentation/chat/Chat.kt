package com.sample.gemini.ui.presentation.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sample.gemini.R
import com.sample.gemini.ui.theme.Purple40

@Composable
fun Chat(navController: NavController = rememberNavController()) {
    val viewModel: ChatViewModel = hiltViewModel()
    val inputText = viewModel.textInput.collectAsStateWithLifecycle()
    val isLoading = viewModel.loader.collectAsStateWithLifecycle()
    val responseText = viewModel.textResponse.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(10.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = "back",
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    navController.popBackStack()
                })
        Column {
            LazyColumn {

            }
            Row(modifier = Modifier.padding(vertical = 20.dp)) {
                OutlinedTextField(
                    value = inputText.value,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(horizontal = 15.dp),
                    shape = RoundedCornerShape(15.dp),
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.cancel),
                            contentDescription = "cancel",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    viewModel.setTextInput("")
                                }
                        )
                    },
                    placeholder = {
                        androidx.compose.material3.Text("Message Gemini..")
                    },
                    textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
                    onValueChange = {
                        viewModel.setTextInput(it)
                    })

                IconButton(
                    onClick = {
                        viewModel.fetchText(inputText.value)
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Purple40),
                    modifier = Modifier.size(60.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_send),
                        contentDescription = "back",
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }
        }
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .verticalScroll(rememberScrollState())
//
//        ) {
//            if (isLoading.value) {
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
//            } else {
//                androidx.compose.material3.Text(responseText.value, color = Color.White)
//            }
//        }
    }
}