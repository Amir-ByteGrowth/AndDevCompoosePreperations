package com.example.navigationgraphreading.ui.screens.secondscreen

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
fun SecondScreen(modifier: Modifier = Modifier,navigate :() ->Unit) {

    Log.d("WhichScreen", "Second Screen")

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().background(color = Color.Yellow)) {
        Column {
            Text(text = "Second Screen")
            Button(onClick = navigate) {
                Text(text = "Move To Third")
            }
        }

    }
}