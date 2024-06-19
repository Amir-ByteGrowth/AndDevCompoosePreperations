package com.example.statehoistingpreperations.propertydrilling

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ChildComponent1LC() {
    Column {
        Text(text = LocalText.current)
        ChildComponent2LC()
    }
}

@Composable
fun ChildComponent2LC() {
    Column {
        Text(text = LocalText.current)
        ChildComponent3LC()
    }
}

@Composable
fun ChildComponent3LC() {
    val updateText = LocalUpdateText.current
    Column {
        Text(text = LocalText.current)
        Button(onClick = {
            updateText("Hello, Compose!")
        }) {
            Text("Update Text")
        }
    }
}

@Composable
fun ButtonClick() {
    LocalUpdateText.current.invoke("jhh")
}