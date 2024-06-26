package com.example.compoosepreperations.ui.screens.rememberupdatedstateeffect

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun RememberUpdateStateEffect(modifier: Modifier = Modifier) {
    var counter by remember { mutableStateOf(0) }
    val latestCount by rememberUpdatedState(counter)

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            Log.d("CounterValue", latestCount.toString())
        }
    }

    Box {
        Button(onClick = { counter++ }) {
            Text(text = "Add")
        }
    }

}