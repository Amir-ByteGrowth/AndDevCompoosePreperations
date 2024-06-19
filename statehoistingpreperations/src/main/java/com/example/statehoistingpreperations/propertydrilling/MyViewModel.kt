package com.example.statehoistingpreperations.propertydrilling

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel :ViewModel() {
    var text by mutableStateOf("Hello, World!")
        private set

    fun updateText(newText: String) {
        text = newText
    }
}