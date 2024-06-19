package com.example.statehoistingpreperations.propertydrilling

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ParentComponent() {
    val (text, setText) = remember { mutableStateOf("Hello, World!") }

    Column {
        ChildComponent1(text, setText)
    }
}

@Composable
fun ChildComponent1(text: String, setText: (String) -> Unit) {
    Column {
        Text(text = text)
        ChildComponent2(text, setText)
    }
}

@Composable
fun ChildComponent2(text: String, setText: (String) -> Unit) {
    Column {
        Text(text = text)
        ChildComponent3(text, setText)
    }
}

@Composable
fun ChildComponent3(text: String, setText: (String) -> Unit) {
    Column {
        Text(text = text)
        Button(onClick = { setText("Hello, Compose!") }) {
            Text("Update Text")
        }
    }
}