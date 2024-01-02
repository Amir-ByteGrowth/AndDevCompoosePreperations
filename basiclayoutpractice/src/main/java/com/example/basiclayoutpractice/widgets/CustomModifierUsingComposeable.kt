package com.example.basiclayoutpractice.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.fade(enable: Boolean): Modifier {
    val alpha = if (enable) 0.2f else 1.0f
    return this then  Modifier.graphicsLayer { this.alpha = alpha }
}


@Composable
fun CustomModifierUsingComposable() {
    var enable by remember { mutableStateOf(false) }
    Column {
        Button(onClick = { enable = !enable }) {
            Text(text = "Click here")
        }
        Box(modifier = Modifier.size(200.dp).fade(enable)
           .background(Color.Green).clickable(onClickLabel = "Amir") {  }, contentAlignment = Alignment.Center)
           {
            Text(text = enable.toString())
        }

    }
}
