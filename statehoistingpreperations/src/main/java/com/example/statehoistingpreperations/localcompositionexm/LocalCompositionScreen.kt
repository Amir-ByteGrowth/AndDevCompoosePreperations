package com.example.statehoistingpreperations.localcompositionexm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


val LocalExample = compositionLocalOf { "Default Value" }

@Composable
fun LocalCompositionScreen(modifier: Modifier = Modifier) {
    CompositionLocalProvider(LocalExample provides "Provided Value") {
        ChildOne()
    }
}

@Composable
fun ChildOne(modifier: Modifier = Modifier) {
    Column {

        Text(text = LocalExample.current)
        Spacer(modifier = modifier.height(10.dp))
        CompositionLocalProvider(LocalExample provides "Provided Value Child 2") {
            Child2()
        }


    }


}

@Composable
fun Child2(modifier: Modifier = Modifier) {
    val exampleValue = LocalExample.current
    Text(text = exampleValue)
}