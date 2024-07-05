package com.example.statehoistingpreperations.localcompositionexm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


val LocalContentAlpha = compositionLocalOf { 1f }

@Composable
fun ProvideContentAlpha(alpha: Float, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalContentAlpha provides alpha, content = content)
}

@Composable
fun ThemedContent() {
    val alpha = LocalContentAlpha.current
    val contentColor = LocalContentColor.current.copy(alpha = alpha)

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp).background(color = Color.Blue)
        ) {
            Text(
                text = "Hello, Themed World!",
                color = LocalContentColor.current,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "This is a demonstration of dynamic theming with alpha.",
                color = LocalContentColor.current,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ThemedContent1() {
    val alpha = LocalContentAlpha.current
    val contentColor = LocalContentColor.current.copy(alpha = alpha)

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "This is a demonstration of dynamic theming with alpha.",
                color = LocalContentColor.current,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun MyAppStatic() {

        Column {
            ProvideContentAlpha(0.3f) {
                ThemedContent()
            }
            ProvideContentAlpha(01f) {
                ThemedContent1()
            }

        }

}
@Composable
fun DescendantExample() {
    // CompositionLocalProviders also work across composable functions
    Text("This Text uses the disabled alpha now")
}