package com.sample.gemini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.ai.client.generativeai.Chat
import com.sample.gemini.ui.presentation.chat.Chat
import com.sample.gemini.ui.presentation.text.Text
import com.sample.gemini.ui.presentation.home.Home
import com.sample.gemini.ui.presentation.image.Image
import com.sample.gemini.ui.theme.GeminiSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            Home(navController)
                        }
                        composable("text") {
                            Text(navController)
                        }
                        composable("image") {
                            Image(navController)
                        }
                        composable("chat") {
                            Chat(navController)
                        }
                    }
                }
            }
        }
    }

}