package com.example.navigationgraphreading.ui.screens.screenone

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun FirstScreen(
    msg: String = "",
    modifier: Modifier = Modifier,
    navigateToTwoParamScreen: () -> Unit={},
    navigateToSecond: () -> Unit,

) {
    Log.d("WhichScreen", "First Screen")
    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Magenta)) {
        Column {
            Text(text = "First Screen")
            Button(onClick = navigateToSecond) {
                Text(text = "Move To Second")
            }
            Button(onClick = navigateToTwoParamScreen) {
                Text(text = "Navigate to two Param")
            }
            if (msg.isNotEmpty()) {
                Text(text = msg)
            }
        }

    }
}