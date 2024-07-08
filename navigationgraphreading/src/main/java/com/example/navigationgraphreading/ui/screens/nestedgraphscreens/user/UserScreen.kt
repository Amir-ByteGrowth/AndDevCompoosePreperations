package com.example.navigationgraphreading.ui.screens.nestedgraphscreens.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun UserScreen(modifier: Modifier = Modifier, navigate: () -> Unit) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "User Screen")
            Button(onClick = navigate) {
                Text(text = "Navigate To Nested Graph")
            }
        }
    }
}