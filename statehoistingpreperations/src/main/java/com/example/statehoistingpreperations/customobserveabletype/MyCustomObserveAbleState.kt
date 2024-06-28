package com.example.statehoistingpreperations.customobserveabletype

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier

@Composable
fun<T> MyCustomObserveAble<T>.asState() : State<T?> {
    return produceState(initialValue = value) {
        val observer : (T) -> Unit ={newValue -> value =newValue}
        observe(observer)
        awaitDispose {  }
    }
}