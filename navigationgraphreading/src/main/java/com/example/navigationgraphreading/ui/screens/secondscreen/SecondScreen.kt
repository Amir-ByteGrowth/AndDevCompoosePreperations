package com.example.navigationgraphreading.ui.screens.secondscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SecondScreen(modifier: Modifier = Modifier,navigate :() ->Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column {
            Text(text = "Second Screen")
            Button(onClick = navigate) {
                Text(text = "Move To Third")
            }
        }

    }
}