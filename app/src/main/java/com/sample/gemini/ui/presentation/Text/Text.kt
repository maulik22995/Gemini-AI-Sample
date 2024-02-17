package com.sample.gemini.ui.presentation.Text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Text(navController: NavController = rememberNavController()) {
    val viewModel: TextViewModel = hiltViewModel()
    var input by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.background(Color.Black)) {

        Row(modifier = Modifier.padding(10.dp)) {
            TextField(
                value = input,
                modifier = Modifier.border(
                    2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                ),
                textStyle = TextStyle(color = Color.White, fontSize = 15.sp),
                onValueChange = {
                    input = it
                })
        }


    }
}