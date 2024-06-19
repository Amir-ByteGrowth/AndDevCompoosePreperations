package com.example.statehoistingpreperations.propertydrilling

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ParentComponentLC() {

    val (text, setText) = remember { mutableStateOf("Hello, World!") }


    CompositionLocalProvider(LocalText provides text, LocalUpdateText provides setText) {
        Column(modifier = Modifier.padding(16.dp)) {
            ChildComponent1LC()
        }
    }
}


