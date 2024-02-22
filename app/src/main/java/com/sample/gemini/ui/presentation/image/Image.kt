package com.sample.gemini.ui.presentation.image

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.sample.gemini.R
import com.sample.gemini.ui.theme.Purple40

@Composable
fun Image(navController: NavController = rememberNavController()) {
    val viewModel: ImageViewModel = hiltViewModel()
    val inputText = viewModel.textInput.collectAsStateWithLifecycle()
    val isLoading = viewModel.loader.collectAsStateWithLifecycle()
    val responseText = viewModel.textResponse.collectAsStateWithLifecycle()
    var photoUri: Uri? by remember { mutableStateOf(null) }
    val context = LocalContext.current
    // Registers a photo picker activity launcher in single-select mode.
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                photoUri = uri
                photoUri?.let {
                    viewModel.prepareImageBitmap(context, it)
                }
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(10.dp),
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            androidx.compose.foundation.Image(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "back",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        navController.popBackStack()
                    })

            Button(onClick = { pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }) {
                Text(text = "Select Image")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())

        ) {
            if (photoUri != null) {
                AsyncImage(
                    model = photoUri,
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(0.5f)
                        .aspectRatio(1f)
                )

                Text(
                    text = "Ask Gemini to anything with context of selected image",
                    modifier = Modifier.padding(15.dp),
                    color = Color.White
                )
            }

            Row(modifier = Modifier.padding(vertical = 20.dp)) {
                OutlinedTextField(
                    value = inputText.value,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(horizontal = 15.dp),
                    shape = RoundedCornerShape(15.dp),
                    trailingIcon = {
                        androidx.compose.foundation.Image(
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
                        Text("Message Gemini..")
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
                    androidx.compose.foundation.Image(
                        painter = painterResource(R.drawable.ic_send),
                        contentDescription = "back",
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }

            if (isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                Text(responseText.value, color = Color.White)
            }
        }
    }
}
