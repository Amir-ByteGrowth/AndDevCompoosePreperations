package com.example.navigationgraphreading.ui.screens.fourthscreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FourthScreen(modifier: Modifier = Modifier,navigate :() ->Unit) {
    Log.d("WhichScreen","Fourth screen")
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column {
            Text(text = "Fourth Screen")
            Button(onClick = navigate) {
                Text(text = "Move To Fifth")
            }
        }

    }
}