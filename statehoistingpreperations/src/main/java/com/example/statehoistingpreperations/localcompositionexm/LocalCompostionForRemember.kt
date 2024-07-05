package com.example.statehoistingpreperations.localcompositionexm

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.core.view.NestedScrollingChild2


val LocalText = staticCompositionLocalOf<String> { error("No Text provided") }
var LocalUpdateText = staticCompositionLocalOf<(String) -> Unit> { error("No UpdateText provided") }


@Composable
fun LocalCompositionForRemember(modifier: Modifier = Modifier) {
    var (text,setText) =rememberSaveable {
       mutableStateOf("")
    }

    var textComposition = compositionLocalOf { text }
    var updateTextComposition = compositionLocalOf { setText }
    CompositionLocalProvider(LocalText provides text, LocalUpdateText provides setText) {
        Column {
            Text(text = text)
            OutlinedTextField(value = text, onValueChange = setText)
            Child1()
        }
    }
}


@Composable
fun Child1(modifier: Modifier = Modifier) {
    Column {
        Text(text = LocalText.current)
        NestedScrollingChild2()
    }
}

@Composable
fun NestedScrollingChild2(modifier: Modifier = Modifier) {
    OutlinedTextField(value = LocalText.current, onValueChange = LocalUpdateText.current)
}