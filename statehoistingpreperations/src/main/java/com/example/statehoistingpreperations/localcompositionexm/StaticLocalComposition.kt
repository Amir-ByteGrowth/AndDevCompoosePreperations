package com.example.statehoistingpreperations.localcompositionexm

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier


var textComposition = staticCompositionLocalOf { "Default" }

@Composable
fun StaticLocalComposition(modifier: Modifier = Modifier) {
    CompositionLocalProvider(textComposition provides "Provided") {
        ChildComp()
    }
}

@Composable
fun ChildComp(modifier: Modifier = Modifier) {
    Column {
        Text(text = textComposition.current)
        ChildComp2()
    }

}

@Composable
fun ChildComp2(modifier: Modifier = Modifier) {
    CompositionLocalProvider( textComposition provides "Provided2") {
        Text(text = textComposition.current)
    }
}