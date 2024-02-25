package com.sample.gemini.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.gemini.R
import com.sample.gemini.presentation.theme.Purple40

@Composable
fun ChatBoxView(onSend: (String) -> Unit) {
    val inputText = remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    fun sendCallBack() {
        onSend(inputText.value)
        keyboardController?.hide()
        inputText.value = ""

    }
    Row(
        modifier = Modifier.padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = inputText.value,
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(15.dp),
            keyboardActions = KeyboardActions(onSearch = {
                sendCallBack()
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "cancel",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            inputText.value = ""
                        }
                )
            },
            placeholder = {
                Text("Message Gemini..")
            },
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            onValueChange = {
                inputText.value = it
            })

        IconButton(
            onClick = {
                sendCallBack()
            },
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Purple40),
            modifier = Modifier.size(45.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_send),
                contentDescription = "back",
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}