package com.example.navigationgraphreading.ui.screens.screenone

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier



@Composable
fun FirstScreen(modifier: Modifier = Modifier,navigateToSecond :() ->Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column {
            Text(text = "First Screen")
            Button(onClick = navigateToSecond) {
                Text(text = "Move To Second")
            }
        }

    }
}