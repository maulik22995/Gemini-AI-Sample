package com.sample.gemini.ui.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sample.gemini.ui.theme.Purple40

@Composable
@Preview(showSystemUi = true)
fun Home(navController: NavController = rememberNavController()) {
    var animate by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        animate = true
    }

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Row {
                AnimatedVisibility(visible = animate, enter = slideInHorizontally(tween(500)) {
                    -it
                }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .aspectRatio(1f)
                            .padding(5.dp)
                            .clickable {
                                navController.navigate("text")
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = Purple40,
                            contentColor = Color.White
                        ),
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "Text\nBased",
                                modifier = Modifier.align(Alignment.Center),
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                AnimatedVisibility(
                    visible = animate,
                    enter = slideIn(tween(500)) { IntOffset(400, 0) },
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .aspectRatio(1f)
                            .padding(5.dp)
                            .clickable {
                                navController.navigate("image")
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = Purple40,
                            contentColor = Color.White
                        ),
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "Image\nBased", modifier = Modifier.align(Alignment.Center),
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }
                }
            }
            AnimatedVisibility(visible = animate, enter = slideIn(tween(500)) {
                IntOffset(0, 1000)
            }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f)
                        .clickable {
                            navController.navigate("chat")
                        }
                        .padding(5.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Purple40,
                        contentColor = Color.White
                    ),
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "Chat\nBased",
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
        }
    }
}