package com.sample.gemini.ui.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sample.gemini.R

@Composable
fun AppHeader(
    onLeftBtnPress: () -> Unit,
    rightLayout: @Composable BoxScope.() -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = "back",
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterStart)
                .clickable {
                    onLeftBtnPress()
                })

        Image(
            painter = painterResource(id = R.drawable.gemini_logo),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(2.72f)
                .align(Alignment.Center)
                .padding(15.dp),
            contentDescription = ""
        )
        rightLayout()
    }
}